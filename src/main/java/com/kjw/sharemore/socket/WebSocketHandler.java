package com.kjw.sharemore.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjw.sharemore.socket.entity.ChatRoom;
import com.kjw.sharemore.socket.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final ChatService chatService;
    private final ObjectMapper objectMapper;
    private String roomId;
    private ChatRoom chatRoom;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String payload = message.getPayload();

        //메시지를 ChatMessage 객체로 변환
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

        //UUID 가져오기
        roomId = chatMessage.getRoomId();

        //CharRoom 찾기
        chatRoom = chatService.findChatRoom(roomId);

        //로직 실행
        chatService.handleMessage(chatRoom, chatMessage,session);
    }

}
