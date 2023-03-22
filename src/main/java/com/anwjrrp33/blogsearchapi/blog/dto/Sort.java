package com.anwjrrp33.blogsearchapi.blog.dto;

import lombok.Getter;

@Getter
public enum Sort {

    ACCURACY("accuracy", "sim"),
    RECENCY("recency", "date");

    private String kakaoValue;
    private String naverValue;

    Sort(String kakaoValue, String naverValue) {
        this.kakaoValue = kakaoValue;
        this.naverValue = naverValue;
    }
}
