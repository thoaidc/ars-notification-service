package com.ars.notificationservice.repository;

import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.dto.SearchChatMessageRequest;
import org.springframework.data.domain.Page;

public interface NotificationRepositoryCustom {
    Page<ChatMessageDTO> getAllWithPaging(SearchChatMessageRequest request);
}
