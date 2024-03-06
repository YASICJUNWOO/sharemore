package com.kjw.sharemore.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ChatRoomResponse {

    private Long id;

    private String targetUserName;

    @Builder.Default
    private String targetUserImage = "https://source.unsplash.com/random/?profile";

    public static ChatRoomResponse toDTO(Long id, String targetUserName) {
        return ChatRoomResponse.builder()
                .id(id)
                .targetUserName(targetUserName)
                .build();
    }

}
