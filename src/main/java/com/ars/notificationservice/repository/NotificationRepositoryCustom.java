package com.ars.notificationservice.repository;

import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.dto.ConversationDTO;
import com.ars.notificationservice.dto.SearchChatMessageRequest;
import org.springframework.data.domain.Page;

public interface NotificationRepositoryCustom {
    Page<ConversationDTO> getAllConversationWithPaging(SearchChatMessageRequest request);
    Page<ChatMessageDTO> getAllConversationMessageWithPaging(SearchChatMessageRequest request);
}
