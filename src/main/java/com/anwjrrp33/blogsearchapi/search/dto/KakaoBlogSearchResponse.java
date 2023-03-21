package com.anwjrrp33.blogsearchapi.search.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class KakaoBlogSearchResponse {

    private List<Document> documents;

    private Meta meta;
}
