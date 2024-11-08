package com.swu.perfuinder.dto.note;

import com.swu.perfuinder.domain.enums.NoteType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class NoteResponse {

    @Getter
    @Schema(title = "NOTE_RES_01 : 각 노트 별 조회용 DTO")
    @Builder
    public static class NoteInfo {
        @Schema(description = "노트 타입", example = "TOP")
        private NoteType noteType;

        @Schema(description = "노트")
        private String note;

    }
}