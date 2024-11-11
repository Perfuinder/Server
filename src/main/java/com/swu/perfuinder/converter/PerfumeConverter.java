package com.swu.perfuinder.converter;

import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeConverter {

    private final NoteConverter noteConverter;

    // 랜덤 계절 향수 정보 DTO로
    public PerfumeResponse.RandomSeasonPerfumeInfo toRandomSeasonPerfumeInfo(Perfume perfume) {
        return PerfumeResponse.RandomSeasonPerfumeInfo.builder()
                .id(perfume.getId())
                .seasonCode(perfume.getSeason().getCode())
                .brand(perfume.getBrand())
                .name(perfume.getName())
                .imageUrl(perfume.getImageUrl())
                .build();
    }

    // 랜덤 브랜드 향수 정보 DTO로
    public PerfumeResponse.RandomBrandPerfumeInfo toRandomBrandPerfumeInfo(Perfume perfume) {
        return PerfumeResponse.RandomBrandPerfumeInfo.builder()
                .id(perfume.getId())
                .name(perfume.getName())
                .description(perfume.getDescription())
                .imageUrl(perfume.getImageUrl())
                .build();
    }

    // Gemini 향수 5종 추천(탐색) DTO로
    public PerfumeResponse.GeminiPerfume toGeminiPerfumeResponse(Perfume perfume, boolean isFavorite) {
        return PerfumeResponse.GeminiPerfume.builder()
                .id(perfume.getId())
                .brand(perfume.getBrand())
                .imageUrl(perfume.getImageUrl())
                .mainNotes(noteConverter.toMainNoteResponse(perfume.getNotes()))
                .description(perfume.getDescription())
                .isFavorite(isFavorite)
                .build();
    }
}