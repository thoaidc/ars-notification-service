package com.ars.notificationservice.resource;

import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.entity.Notification;
import com.ars.notificationservice.service.NotificationService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;

    public ChatController(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    @MessageMapping("/chats")
    public void processMessage(@Payload ChatMessageDTO chatMessage) {
        Notification savedMsg = notificationService.saveMessage(chatMessage);
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setContent(savedMsg.getContent());
        chatMessageDTO.setSenderId(savedMsg.getSenderId());
        chatMessageDTO.setSenderName(savedMsg.getSenderName());
        chatMessageDTO.setReceiverId(savedMsg.getReceiverId());
        chatMessageDTO.setReceiverName(savedMsg.getReceiverName());

        if (Objects.nonNull(savedMsg.getId())) {
            messagingTemplate.convertAndSendToUser(
                String.valueOf(savedMsg.getReceiverId()),
                "/topics/messages",
                savedMsg
            );
        }
    }
}
