package com.swu.perfuinder.dto.common;

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

    // 생성자, 정적 팩토리 메서드 등
}