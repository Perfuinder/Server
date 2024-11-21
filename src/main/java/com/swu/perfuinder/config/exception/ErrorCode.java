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
    PERFUME_NOT_FOUND(500, "P001", "향수를 찾을 수 없습니다."),
    NO_PERFUME_IN_SEASON(500, "P002", "해당 계절의 향수를 찾을 수 없습니다."),
    NO_PERFUME_IN_BRAND(500, "P003", "해당 브랜드의 향수를 찾을 수 없습니다."),
    SEASON_RANDOM_GENERATE_ERROR(500, "P004", "계절 랜덤 생성 중 오류가 발생했습니다."),
    BRAND_RANDOM_GENERATE_ERROR(500, "P005", "브랜드 랜덤 생성 중 오류가 발생했습니다."),
    INVALID_SEASON_CODE(500, "P006", "잘못된 계절 코드입니다."),
    INVALID_PRICE_RANGE_CODE(500, "P007", "잘못된 가격 범위 코드입니다."),
    NO_RECOMMENDED_PERFUME(500, "P008", "추천된 향수가 없습니다."),
    INVALID_BRAND_NAME(500, "P009", "존재하지 않는 브랜드입니다."),
    DUPLICATE_PERFUME_ID(400, "P010","중복된 향수 ID가 존재합니다."),
    INVALID_PERFUME_COUNT(400, "P011","향수 데이터의 수가 맞지 않습니다."),

    // home
    SEASON_GENERATE_ERROR(500, "H001", "현재 계절 생성 중 오류가 발생했습니다."),


    // gemini
    GEMINI_API_ERROR(503, "G001", "Gemini API 호출 중 오류가 발생했습니다."),
    IMAGE_NOT_FOUND(500, "G002", "이미지에서 키워드를 추출할 수 없습니다."),
    FILE_SIZE_EXCEED(400, "G001", "파일 크기가 너무 큽니다. 최대 20MB까지 업로드 가능합니다.");

    private final int status;
    private final String code;
    private final String message;
}