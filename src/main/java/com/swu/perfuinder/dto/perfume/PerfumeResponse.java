package com.swu.perfuinder.dto.perfume;

import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.dto.celebrity.CelebrityResponse;
import com.swu.perfuinder.dto.keyword.KeywordResponse;
import com.swu.perfuinder.dto.note.NoteResponse;
import com.swu.perfuinder.dto.volume.VolumeResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class PerfumeResponse {

    @Getter
    @Schema(title = "PERFUME_RES_01 : 랜덤 계절 향수 추천용 응답 DTO")
    @Builder
    public static class RandomSeasonPerfumeInfo {
        @Schema(description = "향수 ID", example = "0")
        private Long id;

        @Schema(description = "계절 코드", example = "1")
        private int seasonCode; // 계절 코드로 넘겨야 함

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명", example = "오 드 퍼퓸 플레르 드 뽀")
        private String name;

        @Schema(description = "이미지 URL", example = "https://image.sivillage.com/upload/C00001/goods/org/953/231108006980953.jpg?RS=750&SP=1")
        private String imageUrl;
    }

    @Getter
    @Schema(title = "PERFUME_RES_02 : 랜덤 브랜드 향수 조회용 응답 DTO")
    @Builder
    public static class RandomBrandPerfumeInfo {
        @Schema(description = "향수 ID", example = "0")
        private Long id;

        @Schema(description = "향수명", example = "오 드 퍼퓸 플레르 드 뽀")
        private String name;

        @Schema(description = "향수 설명", example = "부드러운 옷 소매를 걷으면 손목에서 날법한 따뜻하고 포근한 체취의 향")
        private String description;

        @Schema(description = "이미지 URL", example = "https://image.sivillage.com/upload/C00001/goods/org/953/231108006980953.jpg?RS=750&SP=1")
        private String imageUrl;
    }

    @Getter
    @Schema(title = "PERFUME_RES_03 : Gemini 검색 결과 추천 향수 5종 응답 DTO")
    @Builder
    public static class GeminiPerfume {
        @Schema(description = "향수 ID", example = "0")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "이미지 URL", example = "https://image.sivillage.com/upload/C00001/goods/org/953/231108006980953.jpg?RS=750&SP=1")
        private String imageUrl;

        @Schema(description = "메인 노트 목록", example = """
        [
            {
                "noteType": "MAIN",
                "note": "플로럴"
            },
            {
                "noteType": "MAIN",
                "note": "머스크"
            },
            {
                "noteType": "MAIN",
                "note": "우디"
            }
        ]
        """)
        private List<NoteResponse.NoteInfo> mainNotes;

        @Schema(description = "향수 설명", example = "부드러운 옷 소매를 걷으면 손목에서 날법한 따뜻하고 포근한 체취의 향")
        private String description;

        @Schema(description = "찜 여부", example = "false")
        private boolean isFavorite;
    }

    @Getter
    @Schema(title = "PERFUME_RES_04 : 향수 상세 정보 응답 DTO")
    @Builder
    public static class PerfumeInfo {
        @Schema(description = "향수 ID", example = "0")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명", example = "오 드 퍼퓸 플레르 드 뽀")
        private String name;

        @Schema(description = "향수 설명", example = "부드러운 옷 소매를 걷으면 손목에서 날법한 따뜻하고 포근한 체취의 향")
        private String description;

        @Schema(description = "이미지 URL", example = "https://image.sivillage.com/upload/C00001/goods/org/953/231108006980953.jpg?RS=750&SP=1")
        private String imageUrl;

        @Schema(description = "성별 코드", example = "1")
        private int genderCode;

        @Schema(description = "계절 코드", example = "1")
        private int seasonCode;

        @Schema(description = "용량별 가격 정보", example = """
       [
           {
               "volume": "ML_75",
               "price": 279000
           }
       ]
       """)
        private List<VolumeResponse> volumes;

        @Schema(description = "메인 노트 목록", example = """
        [
            {
                "noteType": "MAIN",
                "note": "플로럴"
            },
            {
                "noteType": "MAIN",
                "note": "머스크"
            },
            {
                "noteType": "MAIN",
                "note": "우디"
            }
        ]
        """)
        private List<NoteResponse.NoteInfo> mainNotes;

        @Schema(description = "탑 노트 목록", example = """
        [
            {
                "noteType": "TOP",
                "note": "베르가못"
            },
            {
                "noteType": "TOP",
                "note": "이탈리안 만다린"
            }
        ]
        """)
        private List<NoteResponse.NoteInfo> topNotes;

        @Schema(description = "탑 노트 설명", example = "신선하고 활기찬 시트러스의 향")
        private String topDesc;

        @Schema(description = "미들 노트 목록", example = """
        [
            {
                "noteType": "MIDDLE",
                "note": "암브레트 씨앗"
            },
            {
                "noteType": "MIDDLE",
                "note": "암브레트"
            },
            {
                "noteType": "MIDDLE",
                "note": "페어"
            },
            {
                "noteType": "MIDDLE",
                "note": "플럼"
            },
            {
                "noteType": "MIDDLE",
                "note": "아이리스"
            },
            {
                "noteType": "MIDDLE",
                "note": "핑크 페퍼"
            }
        ]
        """)
        private List<NoteResponse.NoteInfo> middleNotes;

        @Schema(description = "미들 노트 설명", example = "세련되고 독특한 향")
        private String middleDesc;

        @Schema(description = "베이스 노트 목록", example = """
        [
            {
                "noteType": "BASE",
                "note": "머스크"
            }
        ]
        """)
        private List<NoteResponse.NoteInfo> baseNotes;

        @Schema(description = "베이스 노트 설명", example = "감각적이고 매혹적인 향")
        private String baseDesc;

        @Schema(description = "키워드 목록", example = """
        [
            {
                "keyword": "강렬함"
            },
            {
                "keyword": "모던"
            },
            {
                "keyword": "중성적"
            }
        ]
        """)
        private List<KeywordResponse> keywords;

        @Schema(description = "연예인 목록", example = """
        [
            {
                "name": "강민경",
                "url": "https://blog.kakaocdn.net/dn/peoxK/btrURo7dI8F/gOznoHWlrV0PP0VlKtvkWK/img.jpg"
            },
            {
                "name": "비비",
                "url": "https://phinf.wevpstatic.net/MjAyNDA0MTFfMTk0/MDAxNzEyODIxMjAyMjU0.6qYZfsGjv_ZoHs4qxY9eKfm2C6UTy7llFfLVagqIe-kg.4EEqZFvg0tuAXZrsOz24Yn3ZVppa4Jf8prMKRQOIKBcg.PNG/60501192280862199bb0597e0-47d7-4926-a53d-418273ffb0c1.png?type=w670"
            },
            {
                "name": "정우",
                "url": "https://i.namu.wiki/i/38OE8GrKVFoH0_wSrZv-fz3Uoaoa0c5qWnsH128gKRwnP2sZv_ne_ScKYExAU3k1JffYMqeC0woDINxsFGnNOA.webp"
            }
        ]
        """)
        private List<CelebrityResponse> celebrities;

        @Schema(description = "찜 여부", example = "false")
        private boolean isFavorite;
    }

    @Getter
    @Schema(title = "PERFUME_RES_05 : 비교 시 향수 정보 조회 응답용 DTO")
    @Builder
    public static class ComparePerfumeInfo {
        @Schema(description = "향수 ID", example = "0")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명", example = "오 드 퍼퓸 플레르 드 뽀")
        private String name;

        @Schema(description = "향수 설명", example = "부드러운 옷 소매를 걷으면 손목에서 날법한 따뜻하고 포근한 체취의 향")
        private String description;

        @Schema(description = "이미지 URL", example = "https://image.sivillage.com/upload/C00001/goods/org/953/231108006980953.jpg?RS=750&SP=1")
        private String imageUrl;

        @Schema(description = "성별 코드", example = "1")
        private int genderCode;

        @Schema(description = "계절 코드", example = "1")
        private int seasonCode;

        @Schema(description = "용량별 가격 정보", example = """
       [
           {
               "volume": "ML_75",
               "price": 279000
           }
       ]
       """)
        private List<VolumeResponse> volumes;

        @Schema(description = "메인 노트 리스트", example = """
        [
            {
                "noteType": "MAIN",
                "note": "플로럴"
            },
            {
                "noteType": "MAIN",
                "note": "머스크"
            },
            {
                "noteType": "MAIN",
                "note": "우디"
            }
        ]
        """)
        private List<NoteResponse.NoteInfo> mainNotes;

        @Schema(description = "탑 노트 설명", example = "신선하고 활기찬 시트러스의 향")
        private String topDesc;

        @Schema(description = "미들 노트 설명", example = "세련되고 독특한 향")
        private String middleDesc;

        @Schema(description = "베이스 노트 설명", example = "감각적이고 매혹적인 향")
        private String baseDesc;

        @Schema(description = "키워드 목록", example = """
        [
            {
                "keyword": "강렬함"
            },
            {
                "keyword": "모던"
            },
            {
                "keyword": "중성적"
            }
        ]
        """)
        private List<KeywordResponse> keywords;

    }

    @Getter
    @Schema(title = "PERFUME_RES_06 : 비교하기 향수 추천 조회용 DTO")
    @Builder
    public static class CompareRecommendPerfume {

        @Schema(description = "향수 ID", example = "0")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명", example = "오 드 퍼퓸 플레르 드 뽀")
        private String name;

        @Schema(description = "향수 설명", example = "부드러운 옷 소매를 걷으면 손목에서 날법한 따뜻하고 포근한 체취의 향")
        private String description;

        @Schema(description = "이미지 URL", example = "https://image.sivillage.com/upload/C00001/goods/org/953/231108006980953.jpg?RS=750&SP=1")
        private String imageUrl;

        @Schema(description = "메인 노트 리스트", example = """
        [
            {
                "noteType": "MAIN",
                "note": "플로럴"
            },
            {
                "noteType": "MAIN",
                "note": "머스크"
            },
            {
                "noteType": "MAIN",
                "note": "우디"
            }
        ]
        """)
        private List<NoteResponse.NoteInfo> mainNotes;
    }

    @Getter
    @Schema(description = "PERFUME_RES_07 : 일반 검색용 DTO")
    @Builder
    public static class SearchPerfume {
        @Schema(description = "향수 ID", example = "0")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명", example = "오 드 퍼퓸 플레르 드 뽀")
        private String name;

        @Schema(description = "향수 설명", example = "부드러운 옷 소매를 걷으면 손목에서 날법한 따뜻하고 포근한 체취의 향")
        private String description;

        @Schema(description = "이미지 URL", example = "https://image.sivillage.com/upload/C00001/goods/org/953/231108006980953.jpg?RS=750&SP=1")
        private String imageUrl;

        @Schema(description = "메인 노트 리스트", example = """
        [
            {
                "noteType": "MAIN",
                "note": "플로럴"
            },
            {
                "noteType": "MAIN",
                "note": "머스크"
            },
            {
                "noteType": "MAIN",
                "note": "우디"
            }
        ]
        """)
        private List<NoteResponse.NoteInfo> mainNotes;

        @Schema(description = "용량별 가격 정보", example = """
       [
           {
               "volume": "ML_75",
               "price": 279000
           }
       ]
       """)
        private List<VolumeResponse> volumes;
    }

    @Getter
    @Schema(description = "PERFUME_RES_08 : 찜한 향수 조회용 DTO")
    @Builder
    public static class FavoritePerfume {
        @Schema(description = "향수 ID", example = "0")
        private Long id;

        @Schema(description = "브랜드", example = "DIPTYQUE")
        private Brand brand;

        @Schema(description = "향수명", example = "오 드 퍼퓸 플레르 드 뽀")
        private String name;

        @Schema(description = "향수 설명", example = "부드러운 옷 소매를 걷으면 손목에서 날법한 따뜻하고 포근한 체취의 향")
        private String description;

        @Schema(description = "이미지 URL", example = "https://image.sivillage.com/upload/C00001/goods/org/953/231108006980953.jpg?RS=750&SP=1")
        private String imageUrl;

        @Schema(description = "키워드 목록", example = """
        [
            {
                "keyword": "강렬함"
            },
            {
                "keyword": "모던"
            },
            {
                "keyword": "중성적"
            }
        ]
        """)
        private List<KeywordResponse> keywords;
    }
}