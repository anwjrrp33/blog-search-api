package com.anwjrrp33.blogsearchapi.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class BlogRequest {

    @NotBlank
    private String query;

    private Sort sort;

    @Min(value = 1)
    private Integer page;

    @Min(value = 1)
    private Integer size;
}
