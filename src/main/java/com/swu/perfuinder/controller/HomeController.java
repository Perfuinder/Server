package com.swu.perfuinder.controller;

import com.swu.perfuinder.apiPayload.ApiResponse;
import com.swu.perfuinder.dto.response.HomeResponse;
import com.swu.perfuinder.service.PerfumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "HomeController", description = "홈 화면")
public class HomeController {
    private final PerfumeService perfumeService;

    @GetMapping("/home")
    @Operation(summary = "홈 화면 조회 API", description = "랜덤 계절 향수와 랜덤 브랜드 향수를 조회합니다.")
    public ApiResponse<HomeResponse.HomeInfo> getHome() {
        HomeResponse.HomeInfo response = perfumeService.getHomeInfo();
        return ApiResponse.success("홈 화면 조회 성공", response);
    }
}