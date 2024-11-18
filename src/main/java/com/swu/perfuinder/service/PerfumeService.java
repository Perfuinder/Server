package com.swu.perfuinder.service;

import com.swu.perfuinder.config.exception.CustomException;
import com.swu.perfuinder.config.exception.ErrorCode;
import com.swu.perfuinder.converter.BrandSearchConverter;
import com.swu.perfuinder.converter.KeywordConverter;
import com.swu.perfuinder.converter.NoteConverter;
import com.swu.perfuinder.converter.PerfumeConverter;
import com.swu.perfuinder.domain.Favorite;
import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.domain.enums.Season;
import com.swu.perfuinder.dto.celebrity.CelebrityResponse;
import com.swu.perfuinder.dto.keyword.KeywordResponse;
import com.swu.perfuinder.dto.note.NoteResponse;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import com.swu.perfuinder.dto.response.HomeResponse;
import com.swu.perfuinder.dto.volume.VolumeResponse;
import com.swu.perfuinder.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;
    private final PerfumeConverter perfumeConverter;
    private final VolumeRepository volumeRepository;
    private final KeywordRepository keywordRepository;
    private final CelebrityRepository celebrityRepository;
    private final FavoriteRepository favoriteRepository;
    private final NoteConverter noteConverter;
    private final KeywordConverter keywordConverter;
    private final BrandSearchConverter brandSearchConverter;

    // 홈 화면 조회
    @Transactional(readOnly = true)
    public HomeResponse.HomeInfo getHomeInfo() {
        Season randomSeason = getRandomSeason();
        Brand randomBrand = getRandomBrand();

        List<Perfume> seasonPerfumeList = perfumeRepository.findRandomBySeason(String.valueOf(randomSeason), 1);
        if (seasonPerfumeList.isEmpty()) {
            throw new CustomException(ErrorCode.NO_PERFUME_IN_SEASON);
        }

        List<Perfume> brandPerfumeList = perfumeRepository.findRandomByBrand(String.valueOf(randomBrand));
        if (brandPerfumeList.isEmpty()) {
            throw new CustomException(ErrorCode.NO_PERFUME_IN_BRAND);
        }

        List<PerfumeResponse.RandomSeasonPerfumeInfo> seasonPerfumes = seasonPerfumeList.stream()
                .map(perfumeConverter::toRandomSeasonPerfumeInfo)
                .collect(Collectors.toList());

        List<PerfumeResponse.RandomBrandPerfumeInfo> brandPerfumes = brandPerfumeList.stream()
                .map(perfumeConverter::toRandomBrandPerfumeInfo)
                .collect(Collectors.toList());

        return HomeResponse.HomeInfo.builder()
                .seasonPerfumes(seasonPerfumes)
                .randomBrandName(randomBrand.name())
                .brandPerfumes(brandPerfumes)
                .build();
    }

    // 랜덤 계절
    private Season getRandomSeason() {
        try {
            Season[] seasons = Season.values();
            int randomIndex = (int) (Math.random() * seasons.length);
            return seasons[randomIndex];
        } catch (Exception e) {
            throw new CustomException(ErrorCode.SEASON_RANDOM_GENERATE_ERROR);
        }
    }

    // 랜덤 브랜드
    private Brand getRandomBrand() {
        try {
            Brand[] brands = Brand.values();
            int randomIndex = (int) (Math.random() * brands.length);
            return brands[randomIndex];
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BRAND_RANDOM_GENERATE_ERROR);
        }
    }

    // 향수 상세 정보 조회
    @Transactional(readOnly = true)
    public PerfumeResponse.PerfumeInfo getPerfumeDetail(Long perfumeId) {
        // 향수 기본 정보 조회
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new CustomException(ErrorCode.PERFUME_NOT_FOUND));

        // 향수 용량별 가격 정보 조회
        List<VolumeResponse.VolumeInfo> volumes = volumeRepository.findByPerfumeId(perfumeId)
                .stream()
                .map(volume -> VolumeResponse.VolumeInfo.builder()
                        .volume(volume.getVolume())
                        .price(volume.getPrice())
                        .build())
                .collect(Collectors.toList());


        // 노트 별 정보 리스트
        List<NoteResponse.NoteInfo> notes = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            notes.add(noteConverter.toNotesInfo(i, perfume, perfume.getNotes()));
        }

        // 키워드 정보 조회
        List<KeywordResponse.KeywordInfo> keywords = keywordRepository.findByPerfumeId(perfumeId);

        // 연예인 정보 조회
        List<CelebrityResponse.CelebrityInfo> celebrities = celebrityRepository.findByPerfumeId(perfumeId);

        return PerfumeResponse.PerfumeInfo.builder()
                .brand(perfume.getBrand())
                .perfumeName(perfume.getName())
                .perfumeDesc(perfume.getDescription())
                .imageUrl(perfume.getImageUrl())
                .genderCode(perfume.getGender().getCode())
                .seasonCode(perfume.getSeason().getCode())
                .priceDTO(volumes)
                .mainNotes(noteConverter.toMainNoteResponse(perfume.getNotes()))
                .keywords(keywordConverter.toKeywordResponse(perfume.getKeywords()))
                .celebrityDTO(celebrities)
                .isFavorite(favoriteRepository.existsByPerfumeId(perfumeId))
                .notes(notes)
                .build();
    }

    // 향수 비교 정보 조회
    @Transactional(readOnly = true)
    public List<PerfumeResponse.ComparePerfumeInfo> getComparePerfumes(List<Long> perfumeIds) {
        List<Perfume> perfumes = perfumeRepository.findAllById(perfumeIds);

        // 요청한 ID가 모두 존재하는지 확인
        if (perfumes.size() != perfumeIds.size()) {
            throw new CustomException(ErrorCode.PERFUME_NOT_FOUND);
        }

        return perfumes.stream()
                .map(perfumeConverter::toComparePerfumeResponse)
                .collect(Collectors.toList());
    }

    // 향수 비교 선택을 위한 5종 추천에 대한 정보 조회
    @Transactional(readOnly = true)
    public List<PerfumeResponse.CompareRecommendPerfume> getCompareRecommendations(List<Long> perfumeIds) {

        List<Perfume> recommendations = perfumeRepository.findByPerfumeIds(perfumeIds);

        return recommendations.stream()
                .map(perfumeConverter::toCompareRecommendPerfume)
                .collect(Collectors.toList());
    }
    
    // 일반 검색
    @Transactional(readOnly = true)
    public List<PerfumeResponse.SearchPerfume> searchPerfumes(boolean isBrandSearch, String query) {
        List<Perfume> perfumes;

        if (isBrandSearch) {
            Brand brand = brandSearchConverter.convertToBrand(query);
            if (brand == null) {
                throw new CustomException(ErrorCode.INVALID_BRAND_NAME); // 예외 처리
            }
            perfumes = perfumeRepository.findByBrand(brand);
        } else {
            perfumes = perfumeRepository.findByNameContaining(query);
            if (perfumes.isEmpty()) {  // 검색 결과가 없는 경우
                throw new CustomException(ErrorCode.PERFUME_NOT_FOUND);
            }
        }

        // volume 정보 별도 조회 (브랜드 검색, 제품명 검색 모두에 적용)
        if (!perfumes.isEmpty()) {
            perfumes = perfumeRepository.findVolumesForPerfumes(perfumes);
        }

        return perfumes.stream()
                .map(perfumeConverter::toSearchPerfume)
                .collect(Collectors.toList());
    }

    // 향수 찜하기 상태 변경
    @Transactional
    public boolean toggleFavorite(Long perfumeId) {
        // 향수 존재 확인
        Perfume perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new CustomException(ErrorCode.PERFUME_NOT_FOUND));

        // 현재 찜하기 상태 확인
        Optional<Favorite> existingFavorite = favoriteRepository.findByPerfumeId(perfumeId);

        if (existingFavorite.isPresent()) {
            // 이미 찜한 경우 -> 찜하기 취소
            favoriteRepository.delete(existingFavorite.get());
            return false;
        } else {
            // 찜하지 않은 경우 -> 찜하기 추가
            Favorite favorite = Favorite.builder()
                    .perfume(perfume)
                    .build();
            favoriteRepository.save(favorite);
            return true;
        }
    }

    // 찜한 향수 리스트 조회
    @Transactional(readOnly = true)
    public List<PerfumeResponse.FavoritePerfume> getFavoritePerfumes() {
        // 찜한 향수 목록 조회
        List<Perfume> favoritePerfumes = perfumeRepository.findAllByFavorites();

        return favoritePerfumes.stream()
                .map(perfumeConverter::toFavoritePerfume)
                .collect(Collectors.toList());
    }
}