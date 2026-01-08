package com.ars.notificationservice.dto;

import com.dct.model.dto.response.AuditingDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageDTO extends AuditingDTO {
    private Integer senderId;
    private String senderName;
    private Integer receiverId;
    private String receiverName;
    private Message message;
    @JsonIgnore
    private String content;

    public ChatMessageDTO() {}

    public ChatMessageDTO(
        Integer id,
        Integer senderId,
        String senderName,
        Integer receiverId,
        String receiverName,
        String content,
        Instant createdDate
    ) {
        super.setId(id);
        super.setCreatedDate(createdDate);
        this.senderId = senderId;
        this.senderName = senderName;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.content = content;
    }

    public static class Message {
        private List<String> images = new ArrayList<>();
        private String content;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
