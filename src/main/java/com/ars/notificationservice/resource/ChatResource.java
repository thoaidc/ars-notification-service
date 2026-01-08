package com.ars.notificationservice.resource;

import com.ars.notificationservice.dto.SearchChatMessageRequest;
import com.ars.notificationservice.service.NotificationService;
import com.dct.model.dto.response.BaseResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications/chats")
public class ChatResource {
    private final NotificationService notificationService;

    public ChatResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public BaseResponseDTO getAllConversationWithPaging(@ModelAttribute SearchChatMessageRequest request) {
        if (request.isConversationDetail()) {
            return notificationService.getAllConversationMessageWithPaging(request);
        }

        return notificationService.getAllConversationWithPaging(request);
    }
}
