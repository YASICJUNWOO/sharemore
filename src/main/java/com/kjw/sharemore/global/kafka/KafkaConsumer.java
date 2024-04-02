package com.kjw.sharemore.global.kafka;

import com.kjw.sharemore.domain.chat.repository.ChatRoomRepository;
import com.kjw.sharemore.domain.chat.service.KafkaChatMessageService;
import com.kjw.sharemore.domain.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final KafkaChatMessageService kafkaChatMessageService;
    private final UserService userService;
    private final ChatRoomRepository chatRoomRepository;

    /*@KafkaListener(topics = "kafka-chat",groupId = "group")
    public void listen(ChatProtocol chatProtocol) {
        System.out.println("Received Messasge in group : " + chatProtocol.getUserName());

        ChatMessageDTO chatMessageDTO = kafkaChatMessageService.saveChatMessage(chatProtocol);
        simpMessagingTemplate.convertAndSend("/topic/chat/"+chatProtocol.getChatRoomId(), chatMessageDTO);
    }*/

}
