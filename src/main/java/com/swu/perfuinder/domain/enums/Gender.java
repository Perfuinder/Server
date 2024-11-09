package com.swu.perfuinder.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Gender {
    UNISEX(0), MALE(1), FEMALE(2);

    private final int code;

    // code로 season 찾기
    public static Gender of(int code) {
        return Arrays.stream(values())
                .filter(g -> g.code == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Gender code: " + code));
    }
}