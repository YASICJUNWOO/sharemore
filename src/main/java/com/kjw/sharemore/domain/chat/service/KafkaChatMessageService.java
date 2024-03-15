package com.kjw.sharemore.domain.chat.service;

import com.kjw.sharemore.domain.chat.dto.ChatMessageDTO;
import com.kjw.sharemore.domain.chat.entity.ChatMessage;
import com.kjw.sharemore.domain.chat.entity.ChatRoom;
import com.kjw.sharemore.domain.chat.repository.ChatMessageRepository;
import com.kjw.sharemore.domain.chat.repository.ChatRoomRepository;
import com.kjw.sharemore.global.config.ChatProtocol;
import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.service.UserQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaChatMessageService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserQueryService userQueryService;

    @Transactional
    public ChatMessageDTO saveChatMessage(ChatProtocol chatProtocol) {
        Users userByEmail = userQueryService.getUserByEmail(chatProtocol.getUserName());
        ChatRoom chatRoom = chatRoomRepository.findById(chatProtocol.getChatRoomId())
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 없습니다."));

        ChatMessage save = chatMessageRepository.save(ChatMessage.toEntity(chatRoom, userByEmail, chatProtocol.getContent()));
        return ChatMessageDTO.toDTO(save, UserResponseDTO.Simple.of(save.getUsers()));
    }

    public List<ChatMessageDTO> getChatMessageList(Long chatRoomId) {
        return chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(chatRoomId).stream()
                .map(e -> {
                    return ChatMessageDTO.toDTO(e, UserResponseDTO.Simple.of(e.getUsers()));
                })
                .toList();
    }

}
