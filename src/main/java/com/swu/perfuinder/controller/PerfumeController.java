package com.swu.perfuinder.controller;

import com.swu.perfuinder.apiPayload.ApiResponse;
import com.swu.perfuinder.config.exception.CustomException;
import com.swu.perfuinder.config.exception.ErrorCode;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import com.swu.perfuinder.service.PerfumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/perfumes")
@Validated
@RequiredArgsConstructor
public class PerfumeController {
    private final PerfumeService perfumeService;

    @Operation(summary = "향수 상세 정보 조회", description = "향수 ID를 통해 향수의 상세 정보를 조회합니다.")
    @GetMapping("/{perfumeID}")
    public ApiResponse<PerfumeResponse.PerfumeInfo> getPerfumeDetail(
            @Parameter(description = "향수 ID", required = true)
            @PathVariable Long perfumeId
    ) {
        return ApiResponse.success(
                "향수 상세 정보 조회 성공",
                perfumeService.getPerfumeDetail(perfumeId)
        );
    }

    @GetMapping("/compare/{perfumeID}")
    @Operation(
            summary = "향수 비교 정보 조회 API",
            description = "선택한 향수들의 상세 정보를 조회합니다."
    )
    @Parameters({
            @Parameter(
                    name = "perfumeId",
                    description = "비교할 향수 ID 목록 ([]안에 콤마로 구분)",
                    example = "[1001,1002]",
                    required = true,
                    in = ParameterIn.PATH
            )
    })
    public ApiResponse<List<PerfumeResponse.ComparePerfumeInfo>> getComparePerfumes(
            @PathVariable List<Integer> perfumeId  // perfumeIds -> perfumeId
    ) {
        // Integer를 Long으로 변환
        List<Long> perfumeIdList = perfumeId.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        // 크기 검증
        if (perfumeIdList.isEmpty() || perfumeIdList.size() > 2) {
            throw new CustomException(ErrorCode.INVALID_PERFUME_COUNT);
        }

        // 중복 검증
        if (perfumeIdList.size() != perfumeIdList.stream().distinct().count()) {
            throw new CustomException(ErrorCode.DUPLICATE_PERFUME_ID);
        }

        return ApiResponse.success(
                "향수 비교 정보 조회 성공",
                perfumeService.getComparePerfumes(perfumeIdList)
        );
    }

    @GetMapping("/compare/recommend/{perfumeIDs}")
    @Operation(
            summary = "비교하기 향수 추천 조회 API",
            description = "선택된 향수들과 비교할 수 있는 향수들을 추천합니다."
    )
    @Parameters({
            @Parameter(
                    name = "perfumeIDs",
                    description = "현재 선택된 향수 ID 목록 (5개 고정, []안에 콤마로 구분)",
                    example = "[1001,1002,1003,1004,1005]",
                    required = true,
                    in = ParameterIn.PATH
            )
    })
    public ApiResponse<List<PerfumeResponse.CompareRecommendPerfume>> getCompareRecommendations(
            @PathVariable List<Integer> perfumeIDs
    ) {
        // 크기 검증
        if (perfumeIDs.size() != 5) {
            throw new CustomException(ErrorCode.INVALID_PERFUME_COUNT);
        }

        List<Long> perfumeIdList = perfumeIDs.stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        return ApiResponse.success(
                "비교 추천 향수 조회 성공",
                perfumeService.getCompareRecommendations(perfumeIdList)
        );
    }

    @GetMapping("/search/{isBrandSearch}/{query}")
    @Operation(
            summary = "향수 일반 검색 API",
            description = "브랜드 또는 제품명으로 향수를 검색합니다."
    )
    @Parameters({
            @Parameter(
                    name = "isBrandSearch",
                    description = "브랜드 검색 여부",
                    example = "true",
                    required = true
            ),
            @Parameter(
                    name = "query",
                    description = "검색어",
                    example = "딥티크 or 로즈",
                    required = true
            )
    })
    public ApiResponse<List<PerfumeResponse.SearchPerfume>> searchPerfumes(
            @PathVariable boolean isBrandSearch,
            @PathVariable String query
    ) {
        if (isBrandSearch) { // 브랜드 건색인 경우
            return ApiResponse.success(
                    "향수 브랜드 검색 성공",
                    perfumeService.searchPerfumes(isBrandSearch, query)
            );
        } else {
            return ApiResponse.success(
                    "향수 제품명 검색 성공",
                    perfumeService.searchPerfumes(isBrandSearch, query)
            );
        }

    }
}
