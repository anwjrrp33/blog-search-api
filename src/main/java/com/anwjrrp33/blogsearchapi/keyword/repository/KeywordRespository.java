package com.anwjrrp33.blogsearchapi.keyword.repository;

import com.anwjrrp33.blogsearchapi.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface KeywordRespository extends JpaRepository<Keyword, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<Keyword> findByKeyword(String keyword);

    Optional<List<Keyword>> findTop10ByOrderByCount();
}
