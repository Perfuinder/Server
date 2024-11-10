package com.swu.perfuinder.apiPayload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "API 공통 응답")
public class ApiResponse<T> {
    @Schema(description = "상태 코드", example = "200")
    private int status;

    @Schema(description = "응답 메시지")
    private String message;

    @Schema(description = "응답 데이터")
    private T data;

    // 성공 응답 생성 메서드들
    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .status(200)
                .message("SUCCESS")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .status(200)
                .message(message)
                .data(data)
                .build();
    }
}