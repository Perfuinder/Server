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
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GeminiService {
    private final GeminiClient geminiClient;
    private final PerfumeRepository perfumeRepository;
    private final PerfumeConverter perfumeConverter;
    private final FavoriteRepository favoriteRepository;

    public List<PerfumeResponse.GeminiPerfume> getGeminiRecommendations(
            PerfumeRequest.GeminiPerfume request,
            String deviceId
    ) {
        // Gemini API 호출하여 추천받기
        String recommendation = geminiClient.getRecommendation(request);
        // 추천 결과 파싱하여 향수 정보 조회
        List<Perfume> recommendedPerfumes = parseAndFindPerfumes(recommendation);

        if (recommendedPerfumes.isEmpty()) {
            throw new CustomException(ErrorCode.NO_RECOMMENDED_PERFUME);
        }

        // 찜하기 상태와 함께 응답 DTO 변환
        return recommendedPerfumes.stream()
                .map(perfume -> perfumeConverter.toGeminiPerfumeResponse(
                        perfume,
                        deviceId != null &&
                                favoriteRepository.existsByPerfumeIdAndDeviceId(perfume.getId(), deviceId)
                ))
                .collect(Collectors.toList());
    }

    // 파싱 뭔가 단단히 잘못 됐음
    private List<Perfume> parseAndFindPerfumes(String recommendation) {
        List<Perfume> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("brand: (.+), name: (.+)");
        Matcher matcher = pattern.matcher(recommendation);

        while (matcher.find()) {
            String brand = matcher.group(1);
            String name = matcher.group(2);

            Perfume perfume = perfumeRepository.findByBrandAndName(
                    Brand.valueOf(brand.trim().toUpperCase()),
                    name.trim()
            ).orElseThrow(() -> new CustomException(ErrorCode.PERFUME_NOT_FOUND));

            results.add(perfume);
        }

        return results;
    }
}