package com.anwjrrp33.blogsearchapi.search.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Meta {

    private boolean isEnd;

    private Integer pageableCount;

    private Integer totalCount;
}
