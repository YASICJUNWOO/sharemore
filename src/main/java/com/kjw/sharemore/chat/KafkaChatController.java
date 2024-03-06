package com.kjw.sharemore.chat;

import com.kjw.sharemore.chat.dto.ChatMessageDTO;
import com.kjw.sharemore.chat.dto.ChatRoomRequest;
import com.kjw.sharemore.chat.dto.ChatRoomResponse;
import com.kjw.sharemore.chat.entity.ChatMessage;
import com.kjw.sharemore.chat.entity.ChatRoom;
import com.kjw.sharemore.chat.service.KafkaChatMessageService;
import com.kjw.sharemore.chat.service.KafkaChatService;
import com.kjw.sharemore.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class KafkaChatController {

    private final KafkaChatService chatService;
    private final KafkaChatMessageService kafkaChatMessageService;
    
    @PostMapping("/room")
    public ChatRoom createChatRoom(@RequestBody ChatRoomRequest request,
                                   @AuthenticationPrincipal Users users) {
        return chatService.createChatRoom(users, request.getTargetUserId());
    }

    @GetMapping("/room")
    public List<ChatRoomResponse> getChatRoomList(@AuthenticationPrincipal Users users) {
        return chatService.getChatRoomList(users);
    }

    @GetMapping
    public List<ChatMessageDTO> getChatMessageList(@RequestParam Long chatRoomId) {
        return kafkaChatMessageService.getChatMessageList(chatRoomId);
    }

}
