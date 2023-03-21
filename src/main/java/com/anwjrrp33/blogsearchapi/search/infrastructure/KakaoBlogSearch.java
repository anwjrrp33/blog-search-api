package com.anwjrrp33.blogsearchapi.search.infrastructure;

import com.anwjrrp33.blogsearchapi.blog.dto.Blog;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogResponse;
import com.anwjrrp33.blogsearchapi.search.domain.BlogSearch;
import com.anwjrrp33.blogsearchapi.search.dto.KakaoBlogSearchRequest;
import com.anwjrrp33.blogsearchapi.search.dto.KakaoBlogSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Order(1)
@RequiredArgsConstructor
public class KakaoBlogSearch implements BlogSearch {

    private final WebClient webClient;

    @Value("${application.kakao-api.uri}")
    private String uri;

    @Value("${application.kakao-api.key}")
    private String key;

    @Override
    public BlogResponse blogSearch(BlogRequest blogRequest) {
        KakaoBlogSearchRequest kakaoBlogSearchRequest = new KakaoBlogSearchRequest(blogRequest);

        KakaoBlogSearchResponse kakaoBlogSearchResponse = webClient.get()
                .uri(kakaoBlogSearchRequest.toURI(uri))
                .header(HttpHeaders.AUTHORIZATION, String.format("KakaoAK %s", key))
                .retrieve()
                .bodyToMono(KakaoBlogSearchResponse.class)
                .log()
                .block();

        return new BlogResponse(
                trans(kakaoBlogSearchResponse),
                blogRequest.getPage(),
                kakaoBlogSearchResponse.getMeta().getTotalCount());
    }

    public List<Blog> trans(KakaoBlogSearchResponse kakaoBlogSearchResponse) {
        return kakaoBlogSearchResponse.getDocuments()
                .stream()
                .map(document -> new Blog(
                        document.getTitle(),
                        document.getContents(),
                        document.getBlogname(),
                        document.getUrl(),
                        document.getDatetime()))
                .collect(Collectors.toList());
    }
}
