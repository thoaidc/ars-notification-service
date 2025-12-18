package com.ars.notificationservice.resource;

import com.ars.notificationservice.dto.MessageDTO;
import com.ars.notificationservice.service.WebSocketClient;
import com.dct.model.dto.response.BaseResponseDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/p/v1/messages")
public class MessageResource {
    private final WebSocketClient webSocketClient;

    public MessageResource(WebSocketClient webSocketClient) {
        this.webSocketClient = webSocketClient;
    }

    @PostMapping
    public BaseResponseDTO sendMessage(@RequestBody MessageDTO message) {
        return webSocketClient.sendMessageToClient(message);
    }
}
