package com.kjw.sharemore.domain.chat.dto;

import com.kjw.sharemore.domain.chat.entity.ChatMessage;
import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChatMessageDTO {

    private Long id;

    private Long chatRoomId;

    private UserResponseDTO.Simple user;

    private String content;

    private LocalDateTime createdAt;

    public static ChatMessageDTO toDTO(ChatMessage chatMessage, UserResponseDTO.Simple user) {
        return ChatMessageDTO.builder()
                .id(chatMessage.getId())
                .chatRoomId(chatMessage.getChatRoom().getId())
                .user(user)
                .content(chatMessage.getContent())
                .createdAt(chatMessage.getCreatedAt())
                .build();
    }

}
