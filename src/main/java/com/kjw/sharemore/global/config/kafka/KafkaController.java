package com.kjw.sharemore.global.config.kafka;

import com.kjw.sharemore.global.config.ChatProtocol;
import com.kjw.sharemore.global.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {

    private final KafkaProducer kafkaProducer;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/publish")
    public void send(@RequestBody ChatProtocol chatProtocol) {
        kafkaProducer.send(chatProtocol);
    }

    @PostMapping("/test")
    public void sendDirect() {
        log.info("sendDirect");
        simpMessagingTemplate.convertAndSend("/topic/group", "gooooooooood");
    }

}
