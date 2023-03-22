package com.anwjrrp33.blogsearchapi.keyword.event;

import com.anwjrrp33.blogsearchapi.keyword.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeywordEventListener implements ApplicationListener<KeywordEvent> {

    private final KeywordService keywordService;


    @Override
    @Async
    public void onApplicationEvent(KeywordEvent event) {
        keywordService.count(event.getKeyword());
    }
}
