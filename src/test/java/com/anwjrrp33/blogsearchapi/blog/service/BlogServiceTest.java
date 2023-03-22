package com.anwjrrp33.blogsearchapi.blog.service;

import com.anwjrrp33.blogsearchapi.blog.dto.Blog;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogResponse;
import com.anwjrrp33.blogsearchapi.blog.dto.Sort;
import com.anwjrrp33.blogsearchapi.common.exception.ApiCallException;
import com.anwjrrp33.blogsearchapi.keyword.service.KeywordService;
import com.anwjrrp33.blogsearchapi.search.domain.BlogSearch;
import com.anwjrrp33.blogsearchapi.search.infrastructure.KakaoBlogSearch;
import com.anwjrrp33.blogsearchapi.search.infrastructure.NaverBlogSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@DisplayName("블로그 검색 단위 테스트")
@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    @InjectMocks
    private BlogService blogService;

    @Mock
    private List<BlogSearch> blogSearches;

    private BlogRequest blogRequest;

    private BlogResponse blogResponse;

    @BeforeEach
    public void setUp() {
        blogRequest = new BlogRequest("우아한유스방", Sort.ACCURACY, 1, 10);
        blogResponse = new BlogResponse(Arrays.asList(new Blog(
                "Title",
                "Contents",
                "Blogname",
                "http://test.tistory.com/",
                LocalDateTime.now())
        ), 1, 1);
    }

    @DisplayName("카카오 블로그 검색 목록을 조회한다.")
    @Test
    void kakaoBlogSearch() {
        KakaoBlogSearch kakaoBlogSearch = Mockito.mock(KakaoBlogSearch.class);
        blogSearches = Arrays.asList(kakaoBlogSearch);
        blogService = new BlogService(blogSearches, Mockito.mock(KeywordService.class));
        when(kakaoBlogSearch.blogSearch(blogRequest)).thenReturn(blogResponse);

        BlogResponse result = blogService.search(blogRequest);

        assertThat(result).isEqualTo(blogResponse);
    }

    @DisplayName("네이버 블로그 검색 목록을 조회한다.")
    @Test
    void naverBlogSearch() {
        NaverBlogSearch naverBlogSearch = Mockito.mock(NaverBlogSearch.class);
        blogSearches = Arrays.asList(naverBlogSearch);
        blogService = new BlogService(blogSearches, Mockito.mock(KeywordService.class));
        when(naverBlogSearch.blogSearch(blogRequest)).thenReturn(blogResponse);

        BlogResponse result = blogService.search(blogRequest);

        assertThat(result).isEqualTo(blogResponse);
    }

    @DisplayName("모든 블로그 API 연동 실패 시 에러가 발생한다.")
    @Test
    void searchApiCallException() {
        blogSearches = Arrays.asList();

        assertThatThrownBy(() -> blogService.search(blogRequest))
                .isInstanceOf(ApiCallException.class);
    }
}
