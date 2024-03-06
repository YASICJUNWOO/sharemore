package com.kjw.sharemore.chat.service;

import com.kjw.sharemore.chat.dto.ChatMessageDTO;
import com.kjw.sharemore.chat.entity.ChatMessage;
import com.kjw.sharemore.chat.entity.ChatRoom;
import com.kjw.sharemore.chat.repository.ChatMessageRepository;
import com.kjw.sharemore.chat.repository.ChatRoomRepository;
import com.kjw.sharemore.global.config.ChatProtocol;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import com.kjw.sharemore.users.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaChatMessageService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserService userService;

    @Transactional
    public ChatMessageDTO saveChatMessage(ChatProtocol chatProtocol) {
        Users userByEmail = userService.getUserByEmail(chatProtocol.getUserName());
        ChatRoom chatRoom = chatRoomRepository.findById(chatProtocol.getChatRoomId())
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 없습니다."));

        ChatMessage save = chatMessageRepository.save(ChatMessage.toEntity(chatRoom, userByEmail, chatProtocol.getContent()));
        return ChatMessageDTO.toDTO(save, UserSimpleResponseDTO.of(save.getUsers()));
    }

    public List<ChatMessageDTO> getChatMessageList(Long chatRoomId) {
        return chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId).stream()
                .map(e->{
                    return ChatMessageDTO.toDTO(e, UserSimpleResponseDTO.of(e.getUsers()));
                })
                .toList();
    }

}
