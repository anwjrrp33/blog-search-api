package com.anwjrrp33.blogsearchapi.search.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Document {

    private String blogname;

    private String contents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private LocalDateTime datetime;

    private String thumnail;

    private String title;

    private String url;
}
