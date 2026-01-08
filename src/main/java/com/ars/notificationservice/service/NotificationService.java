package com.ars.notificationservice.service;

import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.dto.SearchChatMessageRequest;
import com.ars.notificationservice.entity.Notification;
import com.dct.model.dto.response.BaseResponseDTO;

public interface NotificationService {
    BaseResponseDTO getAllWithPaging(SearchChatMessageRequest request);
    Notification saveMessage(ChatMessageDTO messageDTO);
}
