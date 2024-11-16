package com.swu.perfuinder.converter;

import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.domain.Volume;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeConverter {

    private final NoteConverter noteConverter;
    private final VolumeConverter volumeConverter;
    private final KeywordConverter keywordConverter;

    // 랜덤 계절 향수 정보 DTO로
    public PerfumeResponse.RandomSeasonPerfumeInfo toRandomSeasonPerfumeInfo(Perfume perfume) {
        return PerfumeResponse.RandomSeasonPerfumeInfo.builder()
                .perfumeId(perfume.getId())
                .seasonCode(perfume.getSeason().getCode())
                .brand(perfume.getBrand())
                .perfumeName(perfume.getName())
                .imageUrl(perfume.getImageUrl())
                .build();
    }

    // 랜덤 브랜드 향수 정보 DTO로
    public PerfumeResponse.RandomBrandPerfumeInfo toRandomBrandPerfumeInfo(Perfume perfume) {
        return PerfumeResponse.RandomBrandPerfumeInfo.builder()
                .perfumeId(perfume.getId())
                .perfumeName(perfume.getName())
                .perfumeDesc(perfume.getDescription())
                .imageUrl(perfume.getImageUrl())
                .build();
    }

    // Gemini 향수 5종 추천(탐색) DTO로
    public PerfumeResponse.GeminiPerfumeRes toGeminiPerfumeResponse(Perfume perfume, boolean isFavorite) {
        return PerfumeResponse.GeminiPerfumeRes.builder()
                .perfumeId(perfume.getId())
                .brand(perfume.getBrand())
                .imageUrl(perfume.getImageUrl())
                .perfumeName(perfume.getName())
                .mainNotes(noteConverter.toMainNoteResponse(perfume.getNotes()))
                .perfumeDesc(perfume.getDescription())
                .isFavorite(isFavorite)
                .build();
    }

    public PerfumeResponse.ComparePerfumeInfo toComparePerfumeResponse(Perfume perfume) {
        return PerfumeResponse.ComparePerfumeInfo.builder()
                .perfumeId(perfume.getId())
                .brand(perfume.getBrand())
                .perfumeName(perfume.getName())
                .description(perfume.getDescription())
                .imageUrl(perfume.getImageUrl())
                .genderCode(perfume.getGender().getCode())
                .seasonCode(perfume.getSeason().getCode())
                .priceDTO(volumeConverter.toVolumeResponse(perfume.getVolumes()))
                .mainNotes(noteConverter.toMainNoteResponse(perfume.getNotes()))
                .topNoteDesc(perfume.getTopDesc())
                .middleNoteDesc(perfume.getMiddleDesc())
                .baseNoteDesc(perfume.getBaseDesc())
                .keywords(keywordConverter.toKeywordStringResponse(perfume.getKeywords()))
                .build();
    }

}