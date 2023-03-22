package com.anwjrrp33.blogsearchapi.keyword.service;

import com.anwjrrp33.blogsearchapi.common.exception.NotFoundException;
import com.anwjrrp33.blogsearchapi.keyword.domain.Keyword;
import com.anwjrrp33.blogsearchapi.keyword.dto.KeywordResponse;
import com.anwjrrp33.blogsearchapi.keyword.repository.KeywordRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KeywordService {

    private final KeywordRespository keywordRespository;

    public List<KeywordResponse> rank() {
        return keywordRespository.findTop10ByOrderByCount()
                .orElseThrow(NotFoundException::new)
                .stream()
                .map(KeywordResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void count(String keyword) {
        Optional<Keyword> optional = keywordRespository.findByKeyword(keyword);

        if (optional.isPresent()) {
            optional.get().count();
            return;
        }

        keywordRespository.save(Keyword.from(keyword));
    }
}
