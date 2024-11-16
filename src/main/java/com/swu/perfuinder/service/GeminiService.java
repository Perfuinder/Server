package com.swu.perfuinder.service;

import com.swu.perfuinder.config.exception.CustomException;
import com.swu.perfuinder.config.exception.ErrorCode;
import com.swu.perfuinder.converter.PerfumeConverter;
import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.dto.perfume.PerfumeRequest;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import com.swu.perfuinder.external.gemini.GeminiClient;
import com.swu.perfuinder.repository.FavoriteRepository;
import com.swu.perfuinder.repository.PerfumeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GeminiService {
    private final GeminiClient geminiClient;
    private final PerfumeRepository perfumeRepository;
    private final PerfumeConverter perfumeConverter;
    private final FavoriteRepository favoriteRepository;

    // 추천 향수 5종
    public List<PerfumeResponse.GeminiPerfumeRes> getGeminiRecommendations(
            PerfumeRequest.GeminiPerfumeReq request,
            String deviceId
    ) {
        // Gemini API 호출하여 추천받기
        String recommendation = geminiClient.getRecommendation(request);
        // 추천 결과 파싱하여 향수 정보 조회
        List<Perfume> recommendedPerfumes = parseAndFindPerfumes(recommendation);

        if (recommendedPerfumes.isEmpty()) {
            throw new CustomException(ErrorCode.NO_RECOMMENDED_PERFUME);
        }

        // 가격 범위 필터링
        List<Perfume> filteredPerfumes = filterByPriceRange(recommendedPerfumes, request);

        // 찜하기 상태와 함께 응답 DTO 변환
        return recommendedPerfumes.stream()
                .map(perfume -> perfumeConverter.toGeminiPerfumeResponse(
                        perfume,
                        deviceId != null &&
                                favoriteRepository.existsByPerfumeIdAndDeviceId(perfume.getId(), deviceId)
                ))
                .collect(Collectors.toList());
    }
    
    // 브랜드 명 변환
    private Brand convertBrand(String koreanBrand) {
        return switch (koreanBrand.trim()) {
            case "딥티크" -> Brand.DIPTYQUE;
            case "조말론" -> Brand.JOMALONE;
            case "포맨트" -> Brand.FORMENT;
            case "바이레도" -> Brand.BYREDO;
            default -> throw new CustomException(ErrorCode.INVALID_BRAND_NAME);
        };
    }
    // 응답 결과 파싱
    private List<Perfume> parseAndFindPerfumes(String recommendation) {
        List<Perfume> results = new ArrayList<>();
        String[] lines = recommendation.split("\n\n", 2);
        String recommendationContent = lines.length > 1 ? lines[1] : recommendation;

        log.info("Parsing content: {}", recommendationContent);  // 전체 내용 로깅

        Pattern pattern = Pattern.compile("\\d+\\.\\s*brand:\\s*([^,]+),\\s*name:\\s*([^\\n]+)");
        Matcher matcher = pattern.matcher(recommendationContent);

        int count = 0;  // 매칭 횟수 카운트
        while (matcher.find()) {
            count++;
            String brand = matcher.group(1);
            String name = matcher.group(2);

            log.info("Match #{}: brand='{}', name='{}'", count, brand, name);  // 각 매칭 결과 로깅

            try {
                Brand brandEnum = convertBrand(brand);
                log.info("Converted brand: {}", brandEnum);  // 브랜드 변환 결과 로깅

                Perfume perfume = perfumeRepository.findByBrandAndName(
                        brandEnum,
                        name.trim()
                ).orElseThrow(() -> new CustomException(ErrorCode.PERFUME_NOT_FOUND));

                results.add(perfume);
            } catch (Exception e) {
                log.error("Error processing match #{}: {}", count, e.getMessage());  // 에러 로깅
            }
        }

        return results;
    }

    // 가격 필터링
    private List<Perfume> filterByPriceRange(List<Perfume> perfumes, PerfumeRequest.GeminiPerfumeReq request) {
        int priceRangeCode = request.getPriceRangeCode();

        if (priceRangeCode == 0) {
            return perfumes; // 전체 가격 범위
        } else if (priceRangeCode == 6) {
            int minPrice = request.getCustomPriceRangeMin();
            int maxPrice = request.getCustomPriceRangeMax();
            return perfumes.stream()
                    .filter(perfume -> hasVolumeInPriceRange(perfume, minPrice, maxPrice))
                    .collect(Collectors.toList());
        } else {
            int[][] priceRanges = {
                    {0, 50000}, // 5만원 이하
                    {50000, 100000}, // 5-10만원
                    {100000, 200000}, // 10-20만원
                    {200000, 300000}, // 20-30만원
                    {300000, Integer.MAX_VALUE} // 30만원 이상
            };

            int[] selectedRange = priceRanges[priceRangeCode - 1];
            int minPrice = selectedRange[0];
            int maxPrice = selectedRange[1];

            return perfumes.stream()
                    .filter(perfume -> hasVolumeInPriceRange(perfume, minPrice, maxPrice))
                    .collect(Collectors.toList());
        }
    }

    // 용량별 가격 정보 확인
    private boolean hasVolumeInPriceRange(Perfume perfume, int minPrice, int maxPrice) {
        return perfume.getVolumes().stream()
                .anyMatch(volume -> volume.getPrice() >= minPrice && volume.getPrice() <= maxPrice);
    }

    // 이미지 키워드 추출
    public List<String> extractKeywordsFromImage(MultipartFile imageFile)  {
        try {
            // 이미지 파일을 Gemini API에 전송하고 키워드 추출
            byte[] imageBytes = imageFile.getBytes();
            return geminiClient.extractKeywords(imageBytes);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.IMAGE_NOT_FOUND);
        }

    }
}