package com.anwjrrp33.blogsearchapi.keyword.service;

import com.anwjrrp33.blogsearchapi.common.exception.NotFoundException;
import com.anwjrrp33.blogsearchapi.keyword.domain.Keyword;
import com.anwjrrp33.blogsearchapi.keyword.dto.KeywordResponse;
import com.anwjrrp33.blogsearchapi.keyword.repository.KeywordRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("인기 검색어 단위 테스트")
@ExtendWith(MockitoExtension.class)
public class KeywordServiceTest {

    @Mock
    private KeywordRespository keywordRespository;

    @InjectMocks
    private KeywordService keywordService;

    private List<Keyword> keywords;

    @BeforeEach
    public void setUp() {
        keywords = Arrays.asList(Keyword.from("우아한유스방"), Keyword.from("유쾌한스프링방"));
    }

    @DisplayName("인기 검색어 목록을 조회한다.")
    @Test
    void rank() {
        when(keywordRespository.findTop10ByOrderByCount()).thenReturn(Optional.of(keywords));

        List<KeywordResponse> keywordResponses = keywordService.rank();

        assertThat(keywordResponses
                .stream()
                .map(keywordResponse -> keywordResponse.getKeyword())
                .collect(Collectors.toList()))
                .hasSize(keywords.size())
                .containsExactly(keywords.get(0).getKeyword(), keywords.get(1).getKeyword());
    }

    @DisplayName("인기 검색어 목록이 존재하지 않으면 에러가 발생한다.")
    @Test
    void rankNotFoundException() {
        when(keywordRespository.findTop10ByOrderByCount()).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> keywordService.rank())
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("검색어 조회 카운트를 증가시킨다.")
    @Test
    void count() {
        Keyword keyword = keywords.get(0);
        long expect = keyword.getCount();
        when(keywordRespository.findByKeyword("우아한유스방")).thenReturn(Optional.of(keyword));

        Keyword result = keywordService.count(keyword.getKeyword());

        assertThat(result.getCount()).isEqualTo(++expect);
    }

    @DisplayName("검색어가 존재하지 않으면 검색어를 등록한다.")
    @Test
    void countSave() {
        Keyword keyword = keywords.get(0);
        when(keywordRespository.findByKeyword("우아한유스방")).thenReturn(Optional.ofNullable(null));
        when(keywordRespository.save(any(Keyword.class))).thenReturn(keyword);

        Keyword result = keywordService.count(keyword.getKeyword());

        assertAll(
            () -> assertThat(result.getKeyword()).isEqualTo(keyword.getKeyword()),
            () -> assertThat(result.getCount()).isEqualTo(keyword.getCount())
        );
    }
}
