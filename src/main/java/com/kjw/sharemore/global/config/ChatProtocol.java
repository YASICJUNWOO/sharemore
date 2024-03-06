package com.kjw.sharemore.global.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ChatProtocol implements Serializable {

    private Long chatRoomId;
    private String userName;
    private String content;

}
