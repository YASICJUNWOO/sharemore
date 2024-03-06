package com.kjw.sharemore.chat.repository;

import com.kjw.sharemore.chat.entity.ChatRoom;
import com.kjw.sharemore.chat.entity.ChatRoomUser;
import com.kjw.sharemore.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long> {

    List<ChatRoomUser> findByUsers(Users users);

    List<ChatRoomUser> findByChatRoom(ChatRoom chatRoom);

}
