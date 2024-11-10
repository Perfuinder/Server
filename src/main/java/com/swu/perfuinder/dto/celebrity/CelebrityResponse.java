package com.swu.perfuinder.dto.celebrity;

import com.swu.perfuinder.domain.Celebrity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class CelebrityResponse {

    @Getter
    @Schema(title = "CELEBRITY_RES_01 : 향수별 사용하는 연예인 조회용 DTO")
    @Builder
    public static class CelebrityInfo {
        @Schema(description = "이름", example = "고현정")
        private String name;

        @Schema(description = "이미지 url")
        private String url;
    }

}
