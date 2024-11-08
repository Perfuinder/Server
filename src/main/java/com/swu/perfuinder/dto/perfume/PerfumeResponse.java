package com.swu.perfuinder.dto.perfume;

import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.domain.enums.Gender;
import com.swu.perfuinder.domain.enums.Season;
import com.swu.perfuinder.dto.celebrity.CelebrityResponse;
import com.swu.perfuinder.dto.keyword.KeywordResponse;
import com.swu.perfuinder.dto.note.NoteResponse;
import com.swu.perfuinder.dto.volume.VolumeResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.plaf.PanelUI;
import java.util.List;

public class PerfumeResponse {

    @Getter
    @Schema(title = "PERFUME_RES_01 : 랜덤 계절 향수 추천용 응답 DTO")
    @Builder
    public static class RandomSeasonPerfumeInfo {
        @Schema(description = "향수 ID")
        private Long id;

        @Schema(description = "계절 코드")
        private Season season; // 계절 코드로 넘겨야 함

        @Schema(description = "브랜드")
        private Brand brand;

        @Schema(description = "향수명")
        private String name;

        @Schema(description = "이미지 URL")
        private String imageUrl;
    }

    @Getter
    @Schema(title = "PERFUME_RES_02 : 랜덤 브랜드 향수 조회용 응답 DTO")
    @Builder
    public static class RandomBrandPerfumeInfo {
        @Schema(description = "향수 ID")
        private Long id;

        @Schema(description = "향수명")
        private String name;

        @Schema(description = "향수 설명")
        private String description;

        @Schema(description = "이미지 URL")
        private String imageUrl;
    }

    @Getter
    @Schema(title = "PERFUME_RES_03 : Gemini 검색 결과 추천 향수 5종 응답 DTO")
    @Builder
    public static class RecommendPerfume {
        @Schema(description = "향수 ID")
        private Long id;

        @Schema(description = "브랜드")
        private Brand brand;

        @Schema(description = "이미지 URL")
        private String imageUrl;

        @Schema(description = "메인 노트")
        private List<NoteResponse.NoteInfo> mianNotes;

        @Schema(description = "향수 설명")
        private String description;

        @Schema(description = "찜 여부")
        private boolean isFavorite;
    }

    @Getter
    @Schema(title = "PERFUME_RES_04 : 향수 상세 정보 응답 DTO")
    @Builder
    public static class PerfumeInfo {
        @Schema(description = "향수 ID")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명")
        private String name;

        @Schema(description = "향수 설명")
        private String description;

        @Schema(description = "이미지 URL")
        private String imageUrl;

        @Schema(description = "성별", example = "UNISEX")
        private Gender gender;

        @Schema(description = "계절", example = "SPRING")
        private Season season;

        @Schema(description = "용량별 가격 정보")
        private List<VolumeResponse> volumes;

        @Schema(description = "메인 노트 목록")
        private List<NoteResponse.NoteInfo> mainNotes;

        @Schema(description = "메인 노트 설명")
        private String mainDesc;

        @Schema(description = "미들 노트 목록")
        private List<NoteResponse.NoteInfo> middleNotes;

        @Schema(description = "미들 노트 설명")
        private String middleDesc;

        @Schema(description = "베이스 노트 목록")
        private List<NoteResponse.NoteInfo> baseNotes;

        @Schema(description = "베이스 노트 설명")
        private String baseDesc;

        @Schema(description = "키워드 목록")
        private List<KeywordResponse> keywords;

        @Schema(description = "연예인 목록")
        private List<CelebrityResponse> celebrities;

        @Schema(description = "찜 여부")
        private boolean isFavorite;
    }

    @Getter
    @Schema(title = "PERFUME_RES_05 : 비교 시 향수 정보 조회 응답용 DTO")
    @Builder
    public static class ComparePerfumeInfo {
        @Schema(description = "향수 ID")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명")
        private String name;

        @Schema(description = "향수 설명")
        private String description;

        @Schema(description = "이미지 URL")
        private String imageUrl;

        @Schema(description = "성별", example = "UNISEX")
        private Gender gender;

        @Schema(description = "계절", example = "SPRING")
        private Season season;

        @Schema(description = "용량별 가격 정보")
        private List<VolumeResponse> volumes;

        @Schema(description = "대표 노트 리스트")
        private List<NoteResponse.NoteInfo> mainNotes;

        @Schema(description = "메인 노트 설명")
        private String mainDesc;

        @Schema(description = "미들 노트 설명")
        private String middleDesc;

        @Schema(description = "베이스 노트 설명")
        private String baseDesc;

        @Schema(description = "키워드 목록")
        private List<KeywordResponse> keywords;

    }

    @Getter
    @Schema(title = "PERFUME_RES_06 : 비교하기 향수 추천 조회용 DTO")
    @Builder
    public static class CompareRecommendPerfume {

        @Schema(description = "향수 ID")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명")
        private String name;

        @Schema(description = "향수 설명")
        private String description;

        @Schema(description = "이미지 URL")
        private String imageUrl;

        @Schema(description = "대표 노트 리스트")
        private List<NoteResponse.NoteInfo> mainNotes;
    }

    @Getter
    @Schema(description = "PERFUME_RES_07 : 일반 검색용 DTO")
    @Builder
    public static class SearchPerfume {
        @Schema(description = "향수 ID")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명")
        private String name;

        @Schema(description = "향수 설명")
        private String description;

        @Schema(description = "이미지 URL")
        private String imageUrl;

        @Schema(description = "대표 노트 리스트")
        private List<NoteResponse.NoteInfo> mainNotes;

        @Schema(description = "용량별 가격 정보")
        private List<VolumeResponse> volumes;
    }

    @Getter
    @Schema(description = "PERFUME_RES_08 : 찜한 향수 조회용 DTO")
    @Builder
    public static class FavoritePerfume {
        @Schema(description = "향수 ID")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명")
        private String name;

        @Schema(description = "향수 설명")
        private String description;

        @Schema(description = "이미지 URL")
        private String imageUrl;

        @Schema(description = "키워드 목록")
        private List<KeywordResponse> keywords;
    }
}