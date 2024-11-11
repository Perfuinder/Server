package com.swu.perfuinder.controller;

import com.swu.perfuinder.apiPayload.ApiResponse;
import com.swu.perfuinder.dto.perfume.PerfumeRequest;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import com.swu.perfuinder.service.GeminiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gemini")
@RequiredArgsConstructor
@Tag(name = "GeminiController", description = "Gemini 관련 기능")
public class GeminiController {
    private final GeminiService geminiService;

    @PostMapping("/search")
    @Operation(summary = "Gemini 향수 추천 API", description = "입력된 조건에 맞는 향수 5종을 추천합니다.")
    // @Schema(name = "GeminiSearchResponse : Gemini 향수 추천 API의 응답")
    public ApiResponse<List<PerfumeResponse.GeminiPerfume>> getGeminiRecommendations(
            @RequestBody PerfumeRequest.GeminiPerfume request,
            @RequestHeader(value = "Device-Id", required = false) String deviceId
    ) {
        List<PerfumeResponse.GeminiPerfume> recommendations =
                geminiService.getGeminiRecommendations(request, deviceId);
        return ApiResponse.success("향수 추천 성공", recommendations);
    }
}