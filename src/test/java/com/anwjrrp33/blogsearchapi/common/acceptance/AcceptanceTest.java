package com.anwjrrp33.blogsearchapi.common.acceptance;

import com.anwjrrp33.blogsearchapi.common.util.DatabaseCleanup;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @Autowired
    private CacheManager cacheManager;

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        databaseCleanup.execute();

        Cache blogs = cacheManager.getCache("blogs");
        Cache keywords = cacheManager.getCache("keywords");
        blogs.clear();
        keywords.clear();
    }
}
