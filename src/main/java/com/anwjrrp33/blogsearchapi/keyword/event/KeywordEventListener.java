package com.anwjrrp33.blogsearchapi.keyword.event;

import com.anwjrrp33.blogsearchapi.keyword.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class KeywordEventListener {

    private final KeywordService keywordService;

    @Async
    @EventListener
    public void onApplicationEvent(KeywordEvent event) {
        keywordService.count(event.getKeyword());
    }
}
