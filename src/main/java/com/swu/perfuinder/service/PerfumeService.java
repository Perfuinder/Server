package com.swu.perfuinder.service;

import com.swu.perfuinder.config.exception.CustomException;
import com.swu.perfuinder.config.exception.ErrorCode;
import com.swu.perfuinder.converter.PerfumeConverter;
import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.domain.enums.NoteType;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;
    private final PerfumeConverter perfumeConverter;
    private final VolumeRepository volumeRepository;
    private final NoteRepository noteRepository;
    private final KeywordRepository keywordRepository;
    private final CelebrityRepository celebrityRepository;

    // 홈 화면 조회
    public HomeResponse.HomeInfo getHomeInfo() {
        Season randomSeason = getRandomSeason();
        Brand randomBrand = getRandomBrand();

        List<Perfume> seasonPerfumeList = perfumeRepository.findRandomBySeason(String.valueOf(randomSeason), 5);
        if (seasonPerfumeList.isEmpty()) {
            throw new CustomException(ErrorCode.NO_PERFUME_IN_SEASON);
        }

        List<Perfume> brandPerfumeList = perfumeRepository.findRandomByBrand(String.valueOf(randomBrand), 5);
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


        // 노트 정보 조회
        List<NoteResponse.NoteInfo> mainNotes = noteRepository.findByPerfumeIdAndNoteType(perfumeId, NoteType.MAIN);
        List<NoteResponse.NoteInfo> topNotes = noteRepository.findByPerfumeIdAndNoteType(perfumeId, NoteType.TOP);
        List<NoteResponse.NoteInfo> middleNotes = noteRepository.findByPerfumeIdAndNoteType(perfumeId, NoteType.MIDDLE);
        List<NoteResponse.NoteInfo> baseNotes = noteRepository.findByPerfumeIdAndNoteType(perfumeId, NoteType.BASE);

        // 키워드 정보 조회
        List<KeywordResponse.KeywordInfo> keywords = keywordRepository.findByPerfumeId(perfumeId);

        // 연예인 정보 조회
        List<CelebrityResponse.CelebrityInfo> celebrities = celebrityRepository.findByPerfumeId(perfumeId);

        return PerfumeResponse.PerfumeInfo.builder()
                .id(perfume.getId())
                .brand(perfume.getBrand())
                .name(perfume.getName())
                .description(perfume.getDescription())
                .imageUrl(perfume.getImageUrl())
                .genderCode(perfume.getGender().getCode())
                .seasonCode(perfume.getSeason().getCode())
                .volumes(volumes)
                .mainNotes(mainNotes)
                .topNotes(topNotes)
                .middleNotes(middleNotes)
                .baseNotes(baseNotes)
                .topDesc(perfume.getTopDesc())
                .middleDesc(perfume.getMiddleDesc())
                .baseDesc(perfume.getBaseDesc())
                .keywords(keywords)
                .celebrities(celebrities)
                .isFavorite(false)  // 로그인 기능 구현 시 수정 필요
                .build();
    }
}