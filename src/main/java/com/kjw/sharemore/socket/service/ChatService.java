package com.kjw.sharemore.socket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjw.sharemore.socket.ChatMessage;
import com.kjw.sharemore.socket.entity.ChatRoom;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ObjectMapper objectMapper;
    //repository 대신 사용
    private Map<String, ChatRoom> chatRooms;

    //서버가 실행되면 실행
    @PostConstruct
    public void init() {
        chatRooms = new LinkedHashMap<>();
    }

    //채팅방 생성
    public ChatRoom createChatRoom(String name) {
        String randomId = UUID.randomUUID().toString();

        ChatRoom chatRoom = ChatRoom.builder()
                .roomId(randomId)
                .name(name)
                .build();

        //chatRooms(목록)에 채팅방 추가
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    //roomId로 채팅방 찾기
    public ChatRoom findChatRoom(String roomId) {
        return chatRooms.get(roomId);
    }

    //ChatRoom에 있는 모든 session에 메시지 전송
    public void sendToAllMessage(ChatRoom chatRoom, String message) {
        chatRoom.getSessions().forEach(session -> {
            sendMessage(session, message);
        });
    }

    //session에 메시지 전송
    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            throw new IllegalArgumentException("메시지 전송 실패");
        }
    }

    //MessageType에 따라 로직 실행
    public void handleMessage(ChatRoom chatRoom, ChatMessage message, WebSocketSession session) {

        //메시지 타입이 ENTER면
        if (message.getType().equals(ChatMessage.MessageType.ENTER)) {

            //채팅방에 session추가
            chatRoom.getSessions().add(session);

            //메시지 보낸 사람 이름 가져오기
            String sender = message.getSender();
            sendToAllMessage(chatRoom, sender + "님이 입장하셨습니다.");
        }
        else {

            //메시지 보낸 사람 이름 가져오기
            String sender = message.getSender();
            sendToAllMessage(chatRoom, sender + " : " + message.getMessage());
        }
    }

}
