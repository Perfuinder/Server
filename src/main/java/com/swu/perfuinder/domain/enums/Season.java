package com.swu.perfuinder.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Array;
import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Season {
    SPRING(0), SUMMER(1), FALL(2), WINTER(3);

    private final int code;

    // code로 season 찾기
    public static Season of(int code) {
        return Arrays.stream(values())
                .filter(s -> s.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Season code: " + code));
    }

}
