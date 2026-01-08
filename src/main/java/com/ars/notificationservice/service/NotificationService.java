package com.ars.notificationservice.service;

import com.ars.notificationservice.dto.ChatMessageRequest;
import com.ars.notificationservice.dto.SearchChatMessageRequest;
import com.ars.notificationservice.entity.Notification;
import com.dct.model.dto.response.BaseResponseDTO;

public interface NotificationService {
    BaseResponseDTO getAllConversationWithPaging(SearchChatMessageRequest request);
    BaseResponseDTO getAllConversationMessageWithPaging(SearchChatMessageRequest request);
    Notification saveMessage(ChatMessageRequest request);
}
