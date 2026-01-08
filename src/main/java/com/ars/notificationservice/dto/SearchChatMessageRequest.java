package com.ars.notificationservice.dto;

import com.dct.model.dto.request.BaseRequestDTO;

public class SearchChatMessageRequest extends BaseRequestDTO {
    private Integer currentUserId;
    private Integer partnerId;
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

    public Integer getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(Integer currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public void setConversationDetail(boolean conversationDetail) {
        isConversationDetail = conversationDetail;
    }
}
