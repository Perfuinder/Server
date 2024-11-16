package com.swu.perfuinder.converter;

import com.swu.perfuinder.domain.enums.Brand;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BrandSearchConverter {
    // 브랜드 검색어와 enum 값을 매핑하는 Map
    private static final Map<String, Brand> brandMap = new HashMap<>();

    static {

        brandMap.put("딥티크", Brand.DIPTYQUE);
        brandMap.put("딥디크", Brand.DIPTYQUE);
        brandMap.put("조말론", Brand.JOMALONE);
        brandMap.put("포맨트", Brand.FORMENT);
        brandMap.put("바이레도", Brand.BYREDO);
    }

    public Brand convertToBrand(String keyword) {
        return brandMap.get(keyword);
    }
}
