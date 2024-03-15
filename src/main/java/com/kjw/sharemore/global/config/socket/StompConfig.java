/*
package com.kjw.sharemore.socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker //WebSocket 서버를 활성화
public class StompConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/chat").setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //메시지 브로커가 해당 api를 구독하고 있는 클라이언트에게 메시지를 전달
        //registry.enableSimpleBroker("/sub");
        //registry.setApplicationDestinationPrefixes("/pub"); //메시지를 발행하기 위한 prefix

        //RabbitMQ 사용시
        registry.setPathMatcher(new AntPathMatcher(".")); // URL을 / -> .으로
        registry.setApplicationDestinationPrefixes("/pub");  //  @MessageMapping 메서드로 라우팅된다.  Client에서 SEND 요청을 처리
        registry.enableStompBrokerRelay("/queue", "/topic", "/exchange", "/amq/queue");
    }
}
*/
