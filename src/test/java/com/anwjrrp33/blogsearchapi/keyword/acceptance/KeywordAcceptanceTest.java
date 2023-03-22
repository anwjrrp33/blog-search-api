package com.anwjrrp33.blogsearchapi.keyword.acceptance;

import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.Sort;
import com.anwjrrp33.blogsearchapi.common.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.anwjrrp33.blogsearchapi.blog.acceptance.BlogAcceptanceTest.블로그_검색_요청;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("인기 검색어 인수 테스트")
public class KeywordAcceptanceTest extends AcceptanceTest {

    private BlogRequest blogRequest;

    @BeforeEach
    public void setUp() {
        super.setUp();

        blogRequest = new BlogRequest("우아한유스방", Sort.ACCURACY, 1, 10);
    }

    @DisplayName("인기 검색어 목록을 조회한다.")
    @Test
    void rank() {
        블로그_검색_요청(blogRequest);

        ExtractableResponse<Response> response = 인기_키워드_목록_요청(blogRequest);

        인기_키워드_목록_응답됨(response);
    }

    private static ExtractableResponse<Response> 인기_키워드_목록_요청(BlogRequest blogRequest) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/keyword/rank")
                .then().log().all()
                .extract();
    }

    private static void 인기_키워드_목록_응답됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
