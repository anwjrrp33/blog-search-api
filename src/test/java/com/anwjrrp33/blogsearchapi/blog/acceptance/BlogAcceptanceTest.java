package com.anwjrrp33.blogsearchapi.blog.acceptance;

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

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("블로그 검색 인수 테스트")
public class BlogAcceptanceTest extends AcceptanceTest {

    private BlogRequest blogRequest;

    @BeforeEach
    public void setUp() {
        super.setUp();

        blogRequest = new BlogRequest("우아한유스방", Sort.ACCURACY, 1, 10);
    }

    @DisplayName("블로그를 검색한다.")
    @Test
    void createOrder() {
        ExtractableResponse<Response> response = 블로그_검색_요청(blogRequest);

        블로그_검색_응답됨(response);
    }

    private static ExtractableResponse<Response> 블로그_검색_요청(BlogRequest blogRequest) {
        return RestAssured
                .given().log().all()
                .param("query", blogRequest.getQuery())
                .param("sort", blogRequest.getSort())
                .param("page", blogRequest.getPage())
                .param("size", blogRequest.getSize())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/blog/search")
                .then().log().all()
                .extract();
    }

    private static void 블로그_검색_응답됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
