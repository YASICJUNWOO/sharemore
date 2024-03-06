package com.kjw.sharemore.chat.dto;

import com.kjw.sharemore.chat.entity.ChatMessage;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
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

    private UserSimpleResponseDTO user;

    private String content;

    private LocalDateTime createdAt;

    public static ChatMessageDTO toDTO(ChatMessage chatMessage, UserSimpleResponseDTO user) {
        return ChatMessageDTO.builder()
                .id(chatMessage.getId())
                .chatRoomId(chatMessage.getChatRoom().getId())
                .user(user)
                .content(chatMessage.getContent())
                .createdAt(chatMessage.getCreatedAt())
                .build();
    }

}
