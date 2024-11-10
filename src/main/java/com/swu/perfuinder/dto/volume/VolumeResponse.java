package com.swu.perfuinder.dto.volume;


import com.swu.perfuinder.domain.enums.Volume;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class VolumeResponse {

    @Getter
    @Schema(title = "PERFUME_RES_01 : 용량별 가격 정보 응답 DTO")
    @Builder
    public static class VolumeInfo {
        @Schema(description = "용량", example = "ML_50")
        private Volume volume;

        @Schema(description = "가격")
        private Integer price;
    }

}