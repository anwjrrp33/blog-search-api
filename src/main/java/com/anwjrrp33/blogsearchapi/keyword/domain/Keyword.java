package com.anwjrrp33.blogsearchapi.keyword.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String keyword;

    @Column(nullable = false)
    private long count;

    private Keyword(String keyword, long count) {
        if (Objects.isNull(keyword) || keyword.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (Objects.isNull(count)) {
            throw new IllegalArgumentException();
        }
        this.keyword = keyword;
        this.count = count;
    }

    public static Keyword from(String keyword) {
        return new Keyword(keyword, 1);
    }

    public void count() {
        this.count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyword keyword = (Keyword) o;

        return id == keyword.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
