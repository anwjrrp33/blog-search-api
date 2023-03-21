package com.anwjrrp33.blogsearchapi.search.dto;

import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import lombok.Getter;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

@Getter
public class NaverBlogSearchRequest {

    private String query;

    private Integer display;

    private Integer start;

    private String sort;

    public NaverBlogSearchRequest(BlogRequest blogRequest) {
        this.query = blogRequest.getQuery();
        this.display = blogRequest.getSize();
        this.start = start(blogRequest.getPage(), blogRequest.getSize());
        this.sort = blogRequest.getSort().getNaverValue();
    }

    public Integer start(Integer page, Integer size) {
        if (Objects.isNull(page) || Objects.isNull(size)) {
            return 1;
        }
        return (page - 1) * size > 0 ? (page - 1) * size : 1;
    }

    public URI toURI(String uri) {
        return UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("query", query)
                .queryParam("start", start)
                .queryParamIfPresent("display", Optional.ofNullable(display))
                .queryParamIfPresent("sort", Optional.ofNullable(sort))
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUri();
    }
}
