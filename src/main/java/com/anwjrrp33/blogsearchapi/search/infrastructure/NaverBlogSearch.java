package com.anwjrrp33.blogsearchapi.search.infrastructure;

import com.anwjrrp33.blogsearchapi.blog.dto.Blog;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogResponse;
import com.anwjrrp33.blogsearchapi.search.domain.BlogSearch;
import com.anwjrrp33.blogsearchapi.search.dto.KakaoBlogSearchResponse;
import com.anwjrrp33.blogsearchapi.search.dto.NaverBlogSearchRequest;
import com.anwjrrp33.blogsearchapi.search.dto.NaverBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Order(2)
@RequiredArgsConstructor
public class NaverBlogSearch implements BlogSearch {

    private final WebClient webClient;

    @Value("${application.naver-api.uri}")
    private String uri;

    @Value("${application.naver-api.id}")
    private String id;

    @Value("${application.naver-api.secret}")
    private String secret;

    @Override
    @Cacheable(cacheNames = "blogs", key = "#blogRequest", unless = "#result == null", cacheManager = "cacheManager")
    public BlogResponse blogSearch(BlogRequest blogRequest) {
        NaverBlogSearchRequest naverBlogSearchRequest = new NaverBlogSearchRequest(blogRequest);

        NaverBlogSearchResponse naverBlogSearchResponse = webClient.get()
                .uri(naverBlogSearchRequest.toURI(uri))
                .headers(httpHeaders -> {
                    httpHeaders.add("X-Naver-Client-Id", id);
                    httpHeaders.add("X-Naver-Client-Secret", secret);
                })
                .retrieve()
                .bodyToMono(NaverBlogSearchResponse.class)
                .log()
                .block();

        return new BlogResponse(
                trans(naverBlogSearchResponse),
                blogRequest.getPage(),
                naverBlogSearchResponse.getTotal());
    }

    public List<Blog> trans(NaverBlogSearchResponse naverBlogSearchResponse) {
        return naverBlogSearchResponse.getItems()
                .stream()
                .map(item -> new Blog(
                        item.getTitle(),
                        item.getDescription(),
                        item.getBloggername(),
                        item.getLink(),
                        LocalDateTime.parse(item.getPostdate(), DateTimeFormatter.ofPattern("yyyyMMdd"))))
                .collect(Collectors.toList());
    }
}
