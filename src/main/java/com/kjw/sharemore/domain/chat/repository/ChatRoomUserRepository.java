package com.kjw.sharemore.domain.chat.repository;

import com.kjw.sharemore.domain.chat.entity.ChatRoom;
import com.kjw.sharemore.domain.chat.entity.ChatRoomUser;
import com.kjw.sharemore.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {

    List<ChatRoomUser> findByUsers(Users users);

    List<ChatRoomUser> findByChatRoom(ChatRoom chatRoom);

}
