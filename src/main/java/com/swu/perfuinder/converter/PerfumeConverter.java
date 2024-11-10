package com.swu.perfuinder.converter;

import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PerfumeConverter {

    public PerfumeResponse.RandomSeasonPerfumeInfo toRandomSeasonPerfumeInfo(Perfume perfume) {
        return PerfumeResponse.RandomSeasonPerfumeInfo.builder()
                .id(perfume.getId())
                .seasonCode(perfume.getSeason().getCode())
                .brand(perfume.getBrand())
                .name(perfume.getName())
                .imageUrl(perfume.getImageUrl())
                .build();
    }

    public PerfumeResponse.RandomBrandPerfumeInfo toRandomBrandPerfumeInfo(Perfume perfume) {
        return PerfumeResponse.RandomBrandPerfumeInfo.builder()
                .id(perfume.getId())
                .name(perfume.getName())
                .description(perfume.getDescription())
                .imageUrl(perfume.getImageUrl())
                .build();
    }
}