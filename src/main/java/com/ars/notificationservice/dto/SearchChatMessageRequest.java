package com.ars.notificationservice.dto;

import com.dct.model.dto.request.BaseRequestDTO;

public class SearchChatMessageRequest extends BaseRequestDTO {
    private Integer senderId;
    private Integer receiverId;
    private boolean isConversationDetail;

    public boolean isConversationDetail() {
        return isConversationDetail;
    }

    public boolean getIsConversationDetail() {
        return isConversationDetail;
    }

    public void setIsConversationDetail(boolean conversationDetail) {
        this.isConversationDetail = conversationDetail;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }
}
