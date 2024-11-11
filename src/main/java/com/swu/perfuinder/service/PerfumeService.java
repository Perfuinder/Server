package com.swu.perfuinder.service;

import com.swu.perfuinder.config.exception.CustomException;
import com.swu.perfuinder.config.exception.ErrorCode;
import com.swu.perfuinder.converter.PerfumeConverter;
import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.domain.enums.Season;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import com.swu.perfuinder.dto.response.HomeResponse;
import com.swu.perfuinder.repository.PerfumeRepository;
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

    private Season getRandomSeason() {
        try {
            Season[] seasons = Season.values();
            int randomIndex = (int) (Math.random() * seasons.length);
            return seasons[randomIndex];
        } catch (Exception e) {
            throw new CustomException(ErrorCode.SEASON_RANDOM_GENERATE_ERROR);
        }
    }

    private Brand getRandomBrand() {
        try {
            Brand[] brands = Brand.values();
            int randomIndex = (int) (Math.random() * brands.length);
            return brands[randomIndex];
        } catch (Exception e) {
            throw new CustomException(ErrorCode.BRAND_RANDOM_GENERATE_ERROR);
        }
    }
}