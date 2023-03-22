package com.anwjrrp33.blogsearchapi.keyword.controller;

import com.anwjrrp33.blogsearchapi.keyword.dto.KeywordResponse;
import com.anwjrrp33.blogsearchapi.keyword.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping("/keyword/rank")
    public ResponseEntity<List<KeywordResponse>> rank() {
        return ResponseEntity.ok().body(keywordService.rank());
    }
}
