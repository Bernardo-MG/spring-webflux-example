
package com.bernardomg.example.webflux.domain.websocket;

import java.time.Duration;

import org.reactivestreams.Publisher;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.bernardomg.example.webflux.domain.service.ExampleEntityService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public final class MyWebSocketHandler implements WebSocketHandler {

    /**
     * Example entity service.
     */
    private final ExampleEntityService service;

    @Override
    public Mono<Void> handle(final WebSocketSession session) {
        final String           protocol = session.getHandshakeInfo()
            .getSubProtocol();
        final WebSocketMessage message  = session.textMessage("response");
        return doSend(session, Mono.just(message));
    }

    // TODO: workaround for suspected RxNetty WebSocket client issue
    // https://github.com/ReactiveX/RxNetty/issues/560
    private Mono<Void> doSend(final WebSocketSession session, final Publisher<WebSocketMessage> output) {
        return session.send(Mono.delay(Duration.ofMillis(100))
            .thenMany(output));
    }

}
