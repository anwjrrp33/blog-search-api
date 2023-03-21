package com.anwjrrp33.blogsearchapi.search.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class NaverBlogSearchResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, dd MMM yyyy HH:mm:ss Z", locale = "en_US", timezone = "Asia/Seoul")
    private LocalDateTime lastBuildDate;

    private Integer total;

    private Integer start;

    private Integer display;

    private List<Item> items;
}
