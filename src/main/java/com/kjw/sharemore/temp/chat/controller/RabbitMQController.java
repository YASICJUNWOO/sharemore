package com.kjw.sharemore.temp.chat.controller;

import com.kjw.sharemore.temp.chat.ChatDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class RabbitMQController {

    private final static String CHAT_EXCHANGE_NAME = "chat.exchange";
    private final static String CHAT_QUEUE_NAME = "chat.queue";
    private final RabbitTemplate template;

    //receive()는 단순히 큐에 들어온 메세지를 소비만 한다. (현재는 디버그용도)
    @MessageMapping("chat.enter")
    public String enterUser(@Payload ChatDto message) {
        log.info("receive : " + message);
            template.convertAndSend(CHAT_EXCHANGE_NAME, "enter.room.1", message);
        return "good";
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    public ChatDto sendMessage(@Payload ChatDto webSocketChatMessage) {
        return webSocketChatMessage;
    }


}
