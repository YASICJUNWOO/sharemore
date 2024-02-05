package com.kjw.sharemore.socket;

import lombok.Getter;

@Getter
public class ChatMessage {

    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;

}
