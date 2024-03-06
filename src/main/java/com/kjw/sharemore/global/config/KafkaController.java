package com.kjw.sharemore.global.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    private final TestProducer testProducer;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/publish")
    public void send(@RequestBody ChatProtocol chatProtocol) {
        testProducer.send(chatProtocol);
    }

    @PostMapping("/test")
    public void sendDirect() {
        log.info("sendDirect");
        simpMessagingTemplate.convertAndSend("/topic/group", "gooooooooood");
    }

}
