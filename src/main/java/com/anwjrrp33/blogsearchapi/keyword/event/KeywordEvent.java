package com.anwjrrp33.blogsearchapi.keyword.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class KeywordEvent extends ApplicationEvent {
    private final String keyword;

    public KeywordEvent(Object source, String keyword) {
        super(source);
        this.keyword = keyword;
    }
}
