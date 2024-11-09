package com.swu.perfuinder.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PriceRange {
    ALL(0, 0, Integer.MAX_VALUE, "전체"),
    UNDER_50000(1, 0, 50000, "5만원 이하"),
    RANGE_50000_100000(2, 50000, 100000, "5-10만원"),
    RANGE_100000_200000(3, 100000, 200000, "10-20만원"),
    RANGE_200000_300000(4, 200000, 300000, "20-30만원"),
    OVER_300000(5, 300000, Integer.MAX_VALUE, "30만원 이상"),
    CUSTOM(6, null, null, "직접 입력");

    private final int code;
    private final Integer minPrice;
    private final Integer maxPrice;
    private final String description;
}
