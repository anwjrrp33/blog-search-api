package com.anwjrrp33.blogsearchapi.common.config;

import java.time.Duration;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(HttpClient httpClient, ExchangeStrategies exchangeStrategies) {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }

    @Bean
    public HttpClient httpClient(ConnectionProvider connectionProvider) {
        return HttpClient.create(connectionProvider)
                .tcpConfiguration(client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000))
                .wiretap(true)
                .compress(true)
                .followRedirect(true);
    }

    @Bean
    public ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("connection-provider")
                .maxConnections(100)
                .maxIdleTime(Duration.ofSeconds(20))
                .build();
    }

    @Bean
    public ExchangeStrategies exchangeStrategies() {
        return ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024);
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder());
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder());
                })
                .build();
    }
}
