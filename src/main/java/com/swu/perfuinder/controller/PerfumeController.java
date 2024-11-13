package com.swu.perfuinder.controller;

import com.swu.perfuinder.apiPayload.ApiResponse;
import com.swu.perfuinder.config.exception.CustomException;
import com.swu.perfuinder.config.exception.ErrorCode;
import com.swu.perfuinder.dto.perfume.PerfumeRequest;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import com.swu.perfuinder.service.GeminiService;
import com.swu.perfuinder.service.PerfumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/perfumes")
@RequiredArgsConstructor
public class PerfumeController {
    private final PerfumeService perfumeService;

    @Operation(summary = "향수 상세 정보 조회", description = "향수 ID를 통해 향수의 상세 정보를 조회합니다.")
    @GetMapping("/{perfumeId}")
    public ApiResponse<PerfumeResponse.PerfumeInfo> getPerfumeDetail(
            @Parameter(description = "향수 ID", required = true)
            @PathVariable Long perfumeId
    ) {
        return ApiResponse.success(
                "향수 상세 정보 조회 성공",
                perfumeService.getPerfumeDetail(perfumeId)
        );
    }
}
