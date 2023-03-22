package com.anwjrrp33.blogsearchapi.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlogRequest)) {
            return false;
        }
        BlogRequest that = (BlogRequest) o;
        return page == that.page && size == that.size && query.equals(that.query)
                && sort == that.sort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, sort, page, size);
    }
}
