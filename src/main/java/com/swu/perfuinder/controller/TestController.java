package com.swu.perfuinder.controller;

import com.swu.perfuinder.dto.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Test", description = "테스트 API")
public class TestController {

    @Operation(summary = "테스트 API", description = "서버 동작 테스트용 API")
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
