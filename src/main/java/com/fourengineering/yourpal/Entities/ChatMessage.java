package com.fourengineering.yourpal.Entities;

public class ChatMessage {
    private String content;
    private User sender;
    private MessageType type;

    public enum MessageType {
        CHAT, LEAVE, JOIN
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender.getUsername();
    }

    public void setSender(User sender) {
        this.sender.setUsername(sender.getUsername());
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
}
