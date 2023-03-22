package com.anwjrrp33.blogsearchapi.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Blog {

    private String title;

    private String contents;

    private String blogname;

    private String url;

    private LocalDateTime datetime;
}
