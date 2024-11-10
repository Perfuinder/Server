package com.swu.perfuinder.dto.response;

import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class HomeResponse {
    @Getter
    @Schema(title = "HOME_RES_01: 홈 화면 조회 응답 DTO")
    @Builder
    public static class HomeInfo {
        @Schema(description = "계절별 랜덤 향수 추천 목록")
        private List<PerfumeResponse.RandomSeasonPerfumeInfo> seasonPerfumes;

        @Schema(description = "랜덤 브랜드명", example = "DIPTYQUE")
        private String randomBrandName;

        @Schema(description = "랜덤 브랜드 향수 목록")
        private List<PerfumeResponse.RandomBrandPerfumeInfo> brandPerfumes;
    }
}