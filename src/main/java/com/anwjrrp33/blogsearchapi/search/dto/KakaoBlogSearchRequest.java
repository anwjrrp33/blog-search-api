package com.anwjrrp33.blogsearchapi.search.dto;

import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.Sort;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class KakaoBlogSearchRequest {

    private String query;

    private String sort;

    private Integer page;

    private Integer size;

    public KakaoBlogSearchRequest(BlogRequest blogRequest) {
        this.query = blogRequest.getQuery();
        this.sort = blogRequest.getSort() != null ? blogRequest.getSort().getKakaoValue() : Sort.ACCURACY.getKakaoValue();
        this.page = blogRequest.getPage();
        this.size = blogRequest.getSize();
    }

    public URI toURI(String uri) {
        return UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("query", query)
                .queryParamIfPresent("sort", Optional.ofNullable(sort))
                .queryParamIfPresent("page", Optional.ofNullable(page))
                .queryParamIfPresent("size", Optional.ofNullable(size))
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
    }
}
