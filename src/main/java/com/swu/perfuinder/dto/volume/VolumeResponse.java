package com.swu.perfuinder.dto.volume;


import com.swu.perfuinder.domain.enums.Volume;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VolumeResponse {

    @Getter
    @Schema(title = "PERFUME_RES_01 : 용량별 가격 정보 응답 DTO")
    @Builder
    @NoArgsConstructor
    public static class VolumeInfo {
        @Schema(description = "용량", example = "ML_50")
        private Volume volume;

        @Schema(description = "가격")
        private Integer price;

        // JPA 프로젝션을 위한 생성자
        public VolumeInfo(com.swu.perfuinder.domain.enums.Volume volume, Integer price) {
            this.volume = volume;  // Enum 타입이 같으므로 직접 할당 가능
            this.price = price;
        }
    }

}