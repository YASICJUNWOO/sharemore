package com.kjw.sharemore.global.config;

import com.kjw.sharemore.chat.dto.ChatMessageDTO;
import com.kjw.sharemore.chat.entity.ChatMessage;
import com.kjw.sharemore.chat.entity.ChatRoom;
import com.kjw.sharemore.chat.repository.ChatRoomRepository;
import com.kjw.sharemore.chat.service.KafkaChatMessageService;
import com.kjw.sharemore.chat.service.KafkaChatService;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestConsumer {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final KafkaChatMessageService kafkaChatMessageService;
    private final UserService userService;
    private final ChatRoomRepository chatRoomRepository;

    @KafkaListener(topics = "kafka-chat",groupId = "group")
    public void listen(ChatProtocol chatProtocol) {
        System.out.println("Received Messasge in group : " + chatProtocol.getUserName());

        ChatMessageDTO chatMessageDTO = kafkaChatMessageService.saveChatMessage(chatProtocol);
        simpMessagingTemplate.convertAndSend("/topic/chat/"+chatProtocol.getChatRoomId(), chatMessageDTO);
    }

}
