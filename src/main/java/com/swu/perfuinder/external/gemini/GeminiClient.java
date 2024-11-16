package com.swu.perfuinder.external.gemini;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swu.perfuinder.config.exception.CustomException;
import com.swu.perfuinder.config.exception.ErrorCode;
import com.swu.perfuinder.external.gemini.config.GeminiConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.swu.perfuinder.dto.perfume.PerfumeRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class GeminiClient {
    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent";
    private final GeminiConfig geminiConfig;
    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    // 추천 향수 5종
    public String getRecommendation(PerfumeRequest.GeminiPerfumeReq request) {
        try {
            String prompt = buildPrompt(request);

            // Gemini API 요청 형식에 맞게 JSON 구성
            Map<String, Object> requestBody = new HashMap<>();
            Map<String, Object> content = new HashMap<>();
            Map<String, Object> part = new HashMap<>();

            part.put("text", prompt);
            content.put("parts", List.of(part));
            requestBody.put("contents", List.of(content));

            String jsonRequest = objectMapper.writeValueAsString(requestBody);
            log.info("Gemini API Request Body: {}", jsonRequest);

            String response = webClient.post()
                    .uri(GEMINI_API_URL + "?key=" + geminiConfig.getApiKey())  // 여기만 수정
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(jsonRequest)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("Gemini API Response: {}", response);
            return parseGeminiResponse(response);

        } catch (Exception e) {
            log.error("Gemini API 호출 중 오류 발생", e);
            throw new CustomException(ErrorCode.GEMINI_API_ERROR);
        }
    }

    // 추천 향수 5종 프롬프트 systemInstruction 및 정보 등등
    private String buildPrompt(PerfumeRequest.GeminiPerfumeReq request) {
        String systemInstruction = """
    You are a perfume recommendation expert.
    Please follow the conditions below strictly:
    - recommend exactly 5 perfumes that best match the input condition
    - use EXACTLY this format for each recommendation (including colon and comma):
      [number]. brand: [브랜드명], name: [향수명]
    - do NOT use any other format or text
    - each recommendation MUST be in a single line
    - use these exact brand names: 딥티크, 조말론, 포맨트, 바이레도
    - example format:
      1. brand: 딥티크, name: 오 드 퍼퓸 탐 다오
    """;

        String perfumeList = """
            [딥티크 향수 목록]
                오 드 퍼퓸 플레르 드 뽀
                오 드 퍼퓸 오르페옹
                오 드 퍼퓸 필로시코스
                오 드 퍼퓸 도손
                오 드 퍼퓸 탐 다오
                오 드 퍼퓸 오 로즈
                오 드 퍼퓸 오 카피탈
                오 드 퍼퓸 34번가 생제르망
                오 드 퍼퓸 베티베리오
                오 드 퍼퓸 오 드 민떼
                오 드 뚜왈렛 오 데 썽
                오 드 뚜왈렛 로 파피에
                오 드 뚜왈렛 오 로즈
                오 드 뚜왈렛 올렌느
                오 드 뚜왈렛 필로시코스
                오 드 뚜왈렛 탐 다오
                오 드 뚜왈렛 베티베리오
                오 드 뚜왈렛 도손
                오 드 뚜왈렛 오에도
                오 드 뚜왈렛 오프레지아
                오 드 뚜왈렛 로 드 네롤리
                오 드 뚜왈렛 오 드 리에
                
            [조말론 향수 목록]
                로즈 앤 매그놀리아 코롱
                로즈 블러쉬 코롱
                로즈 앰버 코롱
                블랙베리 앤 베이 코롱
                사이프러스 앤 그레이 프바인 코롱 인텐스
                벨벳 로즈 앤 오드 코롱
                머르 앤 통카 코롱 엔텐스
                베티버 앤 골든 마닐라 코롱 인텐스
                튜버로즈 안젤리카 코롱 인텐스
                오드 앤 베르가못 코롱 인텐스\s
                자스민 삼박 앤 메리골드 코롱 인텐스
                다크 앰버 앤 진저 릴리 코롱 인텐스
                스칼렛 포피 코롱 인텐스
                                
            [포맨트 향수 목록]
                시그니처 퍼퓸 코튼 366
                시그니처 퍼퓸 코튼 배쓰
                시그니처 퍼퓸 코튼 썩세스
                시그니처 퍼퓸 바질 테라스
                시그니처 퍼퓸 피그 누아
                시그니처 퍼퓸 상탈 레인
                시그니처 퍼퓸 코튼허그
                시그니처 퍼품 코튼허그 ‘WAVE’
                시그니처 퍼퓸 코튼허그 ‘프로즌’
                시그니처 퍼퓸 코튼허그 에디션  ‘첫 눈’
                시그니처 퍼퓸 코튼허그 백야(白夜) 에디션
                시그니처 퍼퓸 코튼허그 ‘OCEAN’
                시그니처 퍼퓸 코튼브리즈
                시그니처 퍼퓸 코튼 메모리
                시그니처 퍼퓸 코튼 메모리 ‘PINK BLOSSOM’
                시그니처 퍼퓸 코튼 메모리 벛꽃 에디션
                시그니처 퍼퓸 코튼 딜라잇부케
                시그니처 퍼퓸 벨벳허그
                시그니처 퍼퓸 코튼 디어 나잇
                시그니처 퍼퓸 코튼 키스
                시그니처 퍼퓸 NERDY FANTASY
                                
            [바이레도 향수 목록]
                집시 워터 오 드 퍼퓸
                펄프 오 드 퍼퓸
                라 튤립 오 드 퍼퓸
                블랑쉬 오 드 퍼퓸
                아니말리크 오 드 퍼퓸
                우드 이모텔 오 드 퍼퓸
                벨벳 헤이즈 오 드 퍼퓸
                모하비 고스트 오 드 퍼퓸
                나이트 베일스 익스트레잇 드 퍼퓸 바닐 앙티끄
                비블리오티크 오 드 퍼퓸
                슈퍼 시더 오 드 퍼퓸
                인플로레센스 오 드 퍼퓸
                루즈 카오티크 익스트레잇 드 퍼퓸
                익스트레잇 드 퍼퓸 렌드뉘
                익스트레잇 드 퍼퓸 카사블랑카 릴리
                익스트레잇 드 퍼퓸 셀리에
                슬로우 댄스 오 드 퍼
                로즈오브노맨즈랜드 오 드 퍼퓸
                발 다프리크 오 드 퍼퓸
                선데이 코롱 오 드 퍼퓸
                로즈 느와 오 드 퍼퓸
                플라워 헤드 오 드 퍼퓸
                1996 이네즈 & 비누드 오 드 퍼퓸
                미스터 마블러스 오 드 퍼퓸
                아이즈 클로즈드 오 드 퍼퓸
                데 로스 산토스 오 드 퍼퓸            
            """;  // 전체 향수 목록 포함

        String userInput = String.format("""
            [1. 계절] %s
            [2. 가격대] %s
            [3. 키워드] %s
            """,
                getSeasonName(request.getSeasonCode()),
                getPriceRange(request.getPriceRangeCode(), request.getCustomPriceRangeMin(), request.getCustomPriceRangeMax()),
                request.getKeywords()
        );

        return systemInstruction + "\n" + perfumeList + "\n" + userInput;
    }

    private String createRequestBody(String prompt) throws JsonProcessingException {
        Map<String, Object> requestMap = new HashMap<>();
        Map<String, Object> contents = new HashMap<>();
        Map<String, Object> parts = new HashMap<>();

        parts.put("text", prompt);
        contents.put("parts", List.of(parts));
        requestMap.put("contents", List.of(contents));

        return objectMapper.writeValueAsString(requestMap);
    }

    private String parseGeminiResponse(String response) throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(response);
        return root.path("candidates")
                .path(0)
                .path("content")
                .path("parts")
                .path(0)
                .path("text")
                .asText();
    }

    private String getSeasonName(int seasonCode) {
        return switch (seasonCode) {
            case 0 -> "봄";
            case 1 -> "여름";
            case 2 -> "가을";
            case 3 -> "겨울";
            default -> throw new CustomException(ErrorCode.INVALID_SEASON_CODE);
        };
    }

    private String getPriceRange(int priceRangeCode, int customMinPrice, int customMaxPrice) {
        return switch (priceRangeCode) {
            case 0 -> "전체";
            case 1 -> "5만원 이하";
            case 2 -> "5-10만원";
            case 3 -> "10-20만원";
            case 4 -> "20-30만원";
            case 5 -> "30만원 이상";
            case 6 -> String.format("%d원-%d원", customMinPrice, customMaxPrice);
            default -> throw new CustomException(ErrorCode.INVALID_PRICE_RANGE_CODE);
        };
    }

    public List<String> extractKeywords(byte[] imageBytes) {
        try {
            String prompt = buildImagePrompt(imageBytes);

            String jsonRequest = createRequestBody(prompt);
            log.info("Gemini API Request Body: {}", jsonRequest);

            String response = webClient.post()
                    .uri(GEMINI_API_URL + "?key=" + geminiConfig.getApiKey())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(jsonRequest)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            log.info("Gemini API Response: {}", response);
            return parseKeywordsFromResponse(response);

        } catch (Exception e) {
            log.error("Gemini API 호출 중 오류 발생", e);
            throw new CustomException(ErrorCode.GEMINI_API_ERROR);
        }
    }

    // 이미지 키워드 추출 프롬프트 systemInstruction 및 정보 등등
    private String buildImagePrompt(byte[] imageBytes) {
        String systemInstruction = """
            You are an expert in analyzing images and extracting relevant keywords for perfume recommendations.
            Please follow the conditions below strictly:
            - Extract at least 5 keywords that best describe the given image, focusing on perfume-related attributes
            - Each keyword should be in Korean and separated by a comma
            - Use keywords similar to the following examples: 자연적, 우아한, 달콤한, 관능적, 신선한, 시원한, 부드러운
            - Do NOT use any other format or text
            - Example format: keyword1, keyword2, keyword3, keyword4, keyword5
            """;

        return systemInstruction + "\n" + "<image_data>" + new String(imageBytes) + "</image_data>";
    }

    // 응답 결과 파싱하기
    private List<String> parseKeywordsFromResponse(String response) {
        try {
            JsonNode root = objectMapper.readTree(response);
            String keywordsText = root.path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text")
                    .asText();

            String[] keywordsArray = keywordsText.split(",");
            List<String> keywordsList = new ArrayList<>();

            for (String keyword : keywordsArray) {
                String trimmedKeyword = keyword.trim();
                if (!trimmedKeyword.isEmpty()) {
                    keywordsList.add(trimmedKeyword);
                }
            }

            if (keywordsList.isEmpty()) {
                throw new CustomException(ErrorCode.IMAGE_NOT_FOUND);
            }

            return keywordsList;
        } catch (JsonProcessingException e) {
            log.error("Gemini API 응답 파싱 중 오류 발생", e);
            throw new CustomException(ErrorCode.GEMINI_API_ERROR);
        }
    }
}
