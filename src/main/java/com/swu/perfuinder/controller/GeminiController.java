package com.swu.perfuinder.controller;

import com.swu.perfuinder.apiPayload.ApiResponse;
import com.swu.perfuinder.config.exception.CustomException;
import com.swu.perfuinder.config.exception.ErrorCode;
import com.swu.perfuinder.dto.perfume.PerfumeRequest;
import com.swu.perfuinder.dto.perfume.PerfumeResponse;
import com.swu.perfuinder.service.GeminiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gemini")
@RequiredArgsConstructor
@Tag(name = "GeminiController", description = "Gemini 관련 기능 API")
public class GeminiController {
    private final GeminiService geminiService;

    @PostMapping("/search")
    @Operation(summary = "Gemini 향수 추천 API", description = "입력된 조건에 맞는 향수 5종을 추천합니다.")
    // @Schema(name = "GeminiSearchResponse : Gemini 향수 추천 API의 응답")
    public ApiResponse<List<PerfumeResponse.GeminiPerfumeRes>> getGeminiRecommendations(
            @RequestBody PerfumeRequest.GeminiPerfumeReq request
            // @RequestHeader(value = "Device-Id", required = false) String deviceId
    ) {
        List<PerfumeResponse.GeminiPerfumeRes> recommendations =
//                geminiService.getGeminiRecommendations(request, deviceId);
                geminiService.getGeminiRecommendations(request);
        return ApiResponse.success("향수 추천 성공", recommendations);
    }

    @PostMapping(value = "/image/keywords", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Gemini 이미지 키워드 추출 API", description = "이미지의 키워드를 추출합니다.")
    public ApiResponse<PerfumeResponse.GeminiPerfumeKeywords> extractKeywordsFromImage(@RequestParam("imageData") MultipartFile imageData)  {

        List<String> keywords = geminiService.extractKeywordsFromImage(imageData);

        return ApiResponse.success(
                "키워드 추출 성공",
                PerfumeResponse.GeminiPerfumeKeywords.builder()
                        .keywords(keywords)
                        .build()
        );
    }
}