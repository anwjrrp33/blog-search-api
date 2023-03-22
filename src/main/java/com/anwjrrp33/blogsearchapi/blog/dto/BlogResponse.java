package com.anwjrrp33.blogsearchapi.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BlogResponse {

    private List<Blog> blogs;

    private Integer page;

    private Integer size;
}
