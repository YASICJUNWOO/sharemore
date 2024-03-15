package com.kjw.sharemore.global.config.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

//@EnableWebSocket
@Configuration
@EnableWebSocketMessageBroker
//@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    //private final WebSocketHandler webSocketHandler;

    /*@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(webSocketHandler, "/ws/chat")
                .setAllowedOrigins("*");

    }*/

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chatting").setAllowedOriginPatterns("**").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/kafka");
        registry.enableSimpleBroker("/topic");
    }

}
