package com.kjw.sharemore.domain.chat.repository;

import com.kjw.sharemore.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long> {
}
