package com.anwjrrp33.blogsearchapi.search.domain;

import com.anwjrrp33.blogsearchapi.blog.dto.BlogRequest;
import com.anwjrrp33.blogsearchapi.blog.dto.BlogResponse;

public interface BlogSearch {

    BlogResponse blogSearch(BlogRequest blogRequest);
}
