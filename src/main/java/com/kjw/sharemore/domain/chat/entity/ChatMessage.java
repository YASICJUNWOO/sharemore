package com.kjw.sharemore.domain.chat.entity;

import com.kjw.sharemore.global.BaseEntity;
import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Users users;

    private String content;

    public static ChatMessage toEntity(ChatRoom chatRoom, Users users, String content) {
        return ChatMessage.builder()
                .chatRoom(chatRoom)
                .users(users)
                .content(content)
                .build();
    }

}
