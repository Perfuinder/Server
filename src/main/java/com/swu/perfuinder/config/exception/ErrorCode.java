package com.swu.perfuinder.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "C001", "잘못된 입력값입니다."),
    RESOURCE_NOT_FOUND(404, "C002", "리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "C003", "서버 에러가 발생했습니다."),

    // Perfume
    PERFUME_NOT_FOUND(404, "P001", "향수를 찾을 수 없습니다."),
    NO_PERFUME_IN_SEASON(404, "P002", "해당 계절의 향수를 찾을 수 없습니다."),
    NO_PERFUME_IN_BRAND(404, "P003", "해당 브랜드의 향수를 찾을 수 없습니다."),
    SEASON_RANDOM_GENERATE_ERROR(500, "P004", "계절 랜덤 생성 중 오류가 발생했습니다."),
    BRAND_RANDOM_GENERATE_ERROR(500, "P005", "브랜드 랜덤 생성 중 오류가 발생했습니다."),
    INVALID_SEASON_CODE(400, "P006", "잘못된 계절 코드입니다."),
    INVALID_PRICE_RANGE_CODE(400, "P007", "잘못된 가격 범위 코드입니다."),
    NO_RECOMMENDED_PERFUME(404, "P007", "추천된 향수가 없습니다."),

    // gemini
    GEMINI_API_ERROR(500, "G001", "Gemini API 호출 중 오류가 발생했습니다.");

    private final int status;
    private final String code;
    private final String message;
}