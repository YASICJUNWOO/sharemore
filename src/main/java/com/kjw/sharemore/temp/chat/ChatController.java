package com.kjw.sharemore.temp.chat;

import com.kjw.sharemore.temp.chat.entity.ChatRoom;
import com.kjw.sharemore.temp.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("chat-rooms")
public class ChatController {

    private final ChatService chatService;

    //RoomName으로 채팅방 생성
    @PostMapping
    public ChatRoom createRoom(@RequestParam("RoomName") String RoomName) {
        return chatService.createChatRoom(RoomName);
    }

}
