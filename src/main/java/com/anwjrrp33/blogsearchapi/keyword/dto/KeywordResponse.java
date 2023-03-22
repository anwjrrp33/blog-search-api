package com.anwjrrp33.blogsearchapi.keyword.dto;

import com.anwjrrp33.blogsearchapi.keyword.domain.Keyword;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class KeywordResponse {

    private Long id;

    private String keyword;

    private Long count;

    public KeywordResponse(Keyword keyword) {
        this.id = keyword.getId();
        this.keyword = keyword.getKeyword();
        this.count = keyword.getCount();
    }
}
