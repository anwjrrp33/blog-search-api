package com.anwjrrp33.blogsearchapi.blog.service;

import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogResponse;
import com.anwjrrp33.blogsearchapi.common.exception.ApiCallException;
import com.anwjrrp33.blogsearchapi.keyword.service.KeywordService;
import com.anwjrrp33.blogsearchapi.search.domain.BlogSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final List<BlogSearch> blogSearches;

    private final KeywordService keywordService;

    public BlogResponse search(BlogRequest blogRequest) {
        for (int i = 0; i < blogSearches.size(); i++) {
            try {
                BlogResponse blogResponse = blogSearches.get(0).blogSearch(blogRequest);
                keywordService.count(blogRequest.getQuery());
                return blogResponse;
            } catch (RuntimeException e) {
                continue;
            }
        }
        throw new ApiCallException();
    }
}
