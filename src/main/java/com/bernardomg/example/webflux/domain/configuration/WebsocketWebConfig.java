
package com.bernardomg.example.webflux.domain.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import com.bernardomg.example.webflux.domain.service.ExampleEntityService;
import com.bernardomg.example.webflux.domain.websocket.MyWebSocketHandler;

@Configuration
public class WebsocketWebConfig {

    /**
     * Example entity service.
     */
    @Autowired
    private ExampleEntityService service;

    public WebsocketWebConfig() {
        super();
    }

    @Bean
    public HandlerMapping handlerMapping() {
        final Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/websocket/entity", new MyWebSocketHandler(service));
        final int order = -1; // before annotated controllers

        return new SimpleUrlHandlerMapping(map, order);
    }

}
