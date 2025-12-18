package com.ars.notificationservice.service;

import com.ars.notificationservice.dto.MessageDTO;
import com.dct.model.dto.response.BaseResponseDTO;

public interface WebSocketClient {
    BaseResponseDTO sendMessageToClient(MessageDTO message);
}
