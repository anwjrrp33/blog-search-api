package com.anwjrrp33.blogsearchapi.blog.service;

import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogResponse;
import com.anwjrrp33.blogsearchapi.common.exception.ApiCallException;
import com.anwjrrp33.blogsearchapi.keyword.event.KeywordEvent;
import com.anwjrrp33.blogsearchapi.search.domain.BlogSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final List<BlogSearch> blogSearches;

    private final ApplicationEventPublisher eventPublisher;

    public BlogResponse search(BlogRequest blogRequest) {
        eventPublisher.publishEvent(new KeywordEvent(this, blogRequest.getQuery()));

        return blogSearches.stream()
                .map(blogSearch -> safeBlogSearch(blogSearch, blogRequest))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(ApiCallException::new);
    }

    private BlogResponse safeBlogSearch(BlogSearch blogSearch, BlogRequest blogRequest) {
        try {
            return blogSearch.blogSearch(blogRequest);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
