package com.kjw.sharemore.chat.entity;

import com.kjw.sharemore.users.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChatRoomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    public static ChatRoomUser toEntity(ChatRoom chatRoom, Users users) {
        return ChatRoomUser.builder()
                .chatRoom(chatRoom)
                .users(users)
                .build();
    }
}
