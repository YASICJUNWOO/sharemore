package com.kjw.sharemore.domain.chat.service;

import com.kjw.sharemore.domain.chat.dto.ChatRoomResponse;
import com.kjw.sharemore.domain.chat.entity.ChatRoom;
import com.kjw.sharemore.domain.chat.entity.ChatRoomUser;
import com.kjw.sharemore.domain.chat.repository.ChatRoomRepository;
import com.kjw.sharemore.domain.chat.repository.ChatRoomUserRepository;
import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    private final UserRepository userRepository;

    @Transactional
    public ChatRoom createChatRoom(Users firstUser, Long secondUserId) {
        ChatRoom save = chatRoomRepository.save(ChatRoom.builder().build());
        Users secondUser = userRepository.findById(secondUserId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));
        chatRoomUserRepository.save(ChatRoomUser.toEntity(save, firstUser));
        chatRoomUserRepository.save(ChatRoomUser.toEntity(save, secondUser));
        return save;
    }

    public List<ChatRoomResponse> getChatRoomList(Users users) {
        log.info("users : {}", users.getName());
        //다른 유저 이름
        List<ChatRoom> chatRoomList = chatRoomUserRepository.findByUsers(users).stream()
                .map(ChatRoomUser::getChatRoom)
                .toList();

        return chatRoomList.stream()
                .map(e -> {
                    Users otherUser = chatRoomUserRepository.findByChatRoom(e).stream()
                            .filter(chatRoomUser -> !chatRoomUser.getUsers().getEmail().equals(users.getEmail()))
                            .map(ChatRoomUser::getUsers)
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("채팅방에 상대방이 없습니다."));
                    return ChatRoomResponse.toDTO(e.getId(), otherUser.getName());
                })
                .toList();

    }

}
