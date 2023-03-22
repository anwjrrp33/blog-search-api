package com.anwjrrp33.blogsearchapi.blog.service;

import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogResponse;
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
        for (BlogSearch blogSearch : blogSearches) {
            try {
                BlogResponse blogResponse = blogSearch.blogSearch(blogRequest);
                keywordService.count(blogRequest.getQuery());
                return blogResponse;
            } catch (RuntimeException e) {
                continue;
            }
        }
        throw   new RuntimeException();
    }
}
