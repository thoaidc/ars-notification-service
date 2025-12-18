package com.ars.notificationservice.service.impl;

import com.ars.notificationservice.dto.MessageDTO;
import com.ars.notificationservice.service.WebSocketClient;
import com.dct.model.dto.response.BaseResponseDTO;
import com.dct.model.exception.BaseBadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WebSocketClientImpl implements WebSocketClient {
    private static final Logger log = LoggerFactory.getLogger(WebSocketClientImpl.class);
    private static final String ENTITY_NAME = "com.sds.easypos.websocketserver.service.impl.WebSocketClientImpl";
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketClientImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public BaseResponseDTO sendMessageToClient(MessageDTO message) {
        if (!StringUtils.hasText(message.getTopic())) {
            throw new BaseBadRequestException(ENTITY_NAME, "Topic not found!");
        }

        try {
            if (StringUtils.hasText(message.getClientId())) {
                messagingTemplate.convertAndSendToUser(message.getClientId(), message.getTopic(), message.getContent());
            } else {
                messagingTemplate.convertAndSend(message.getTopic(), message.getContent());
            }

            return BaseResponseDTO.builder().ok();
        } catch (Exception e) {
            log.error("[ERROR_SEND_MESSAGE_SOCKET] - payload: {}, error: {}", message, e.getMessage());
            throw e;
        }
    }
}
