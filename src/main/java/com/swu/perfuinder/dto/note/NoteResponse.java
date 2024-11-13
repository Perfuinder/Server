package com.swu.perfuinder.dto.note;

import com.swu.perfuinder.domain.enums.NoteType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
import java.util.List;

public class NoteResponse {

    @Getter
    @Schema(title = "NOTE_RES_01 : 탑, 미들, 베이스별 노트 및 설명 조회용 DTO")
    @Builder
    public static class NoteInfo {
        @Schema(description = "노트 코드 -> 0: TOP, 1: MIDDLE, 2: BASE", example = "0")
        private int typeCode;

        @Schema(description = "각 노트 리스트", example = "[\"플로럴\", \"우디\", \"시트러스\", \"달콤한\"]")
        private List<String> notes = new ArrayList<>();

        @Schema(description = "설명", example = "신선하고 활기찬 시트러스의 향")
        private String desc;

    }

}