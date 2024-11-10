package com.swu.perfuinder.dto.keyword;

import com.swu.perfuinder.domain.enums.Keyword;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;


public class KeywordResponse {

    @Getter
    @Schema(title = "KEYWORD_RES_01 : 키워드 응답 DTO")
    @Builder
    public static class KeywordInfo {
        @Schema(description = "키워드", example = "우아한")
        private Keyword keyword;
    }

}