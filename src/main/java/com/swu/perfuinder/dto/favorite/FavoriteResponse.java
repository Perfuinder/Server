package com.swu.perfuinder.dto.favorite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;


public class FavoriteResponse {

    @Getter
    @Schema(title = "FAVORITE_RES_01 : 찜하기 상태 응답 DTO")
    @Builder
    public static class FavoriteInfo {
        @Schema(description = "향수 ID")
        private Long perfumeId;

        @Schema(description = "찜 상태")
        private boolean isFavorite;
    }

}
