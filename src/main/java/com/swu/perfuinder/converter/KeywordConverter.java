package com.swu.perfuinder.converter;

import com.swu.perfuinder.domain.PerfumeKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class KeywordConverter {

    public List<String> toKeywordResponse(List<PerfumeKeyword> keywords) {
        return keywords.stream()
                .map(keyword -> keyword.getKeyword().name())  // enum의 name() 메소드 사용
                .collect(Collectors.toList());
    }
}
