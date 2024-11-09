package com.swu.perfuinder.dto.perfume;

import com.swu.perfuinder.dto.keyword.KeywordResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class PerfuemRequest {

    @Getter
    @Schema(title = "PERFUME_REQ_01 : GEMINI 검색 요청 DTO")
    public static class GeminiPerfume {

        @Schema(description = "키워드 목록", example = "포근한, 시원한")
        private String keywords;

        @Schema(description = "계절 코드", example = "3")
        private int seasonCode;

        @Schema(description = "가격 범위 코드", example = "3",
                title = "0: 전체, 1: 5만원 이하, 2: 5-10만원, 3: 10-20만원, 4: 20-30만원, 5: 30만원 이상, 6: 직접 입력")
        private int priceRangeCode;

        @Schema(description = "최소 가격 (직접 입력 시: 가격 범위 코드가 6일 때)", example = "0")
        private int customMinPrice;

        @Schema(description = "최대 가격 (직접 입력 시: 가격 범위 코드가 6일 때)", example = "100000")
        private int customMaxPrice;

    }

}
