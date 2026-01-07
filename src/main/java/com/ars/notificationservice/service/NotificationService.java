package com.ars.notificationservice.service;

import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.dto.SearchChatMessageRequest;
import com.dct.model.dto.response.BaseResponseDTO;

public interface NotificationService {
    BaseResponseDTO getAllWithPaging(SearchChatMessageRequest request);
    void saveMessage(ChatMessageDTO messageDTO);
}
