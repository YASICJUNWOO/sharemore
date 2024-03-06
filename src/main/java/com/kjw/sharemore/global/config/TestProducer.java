package com.kjw.sharemore.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestProducer {

    private final KafkaTemplate<String, ChatProtocol> kafkaTemplate;

    public void send(ChatProtocol chatProtocol) {
        log.info("kafkaTemplate");
        kafkaTemplate.send("kafka-chat", chatProtocol);
    }

}
