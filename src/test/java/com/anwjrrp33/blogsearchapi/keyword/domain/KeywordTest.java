package com.anwjrrp33.blogsearchapi.keyword.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Keyword 도메인 테스트")
class KeywordTest {

    @DisplayName("Keyword 생성 테스트")
    @Test
    void constructor() {
        assertThatCode(() -> { Keyword.from("우아한유스방"); })
                .doesNotThrowAnyException();
    }

    @DisplayName("Keyword 생성 후 Count 기본 값(1) 테스트")
    @Test
    void constructorCount() {
        Keyword keyword = Keyword.from("우아한유스방");

        assertThat(keyword.getCount()).isOne();
    }

    @DisplayName("Keyword의 keyword가 Null 또는 빈 값인 경우 예외 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    void constructorValidateKeyword(String keyword) {
        assertThatThrownBy(() -> Keyword.from(keyword))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
