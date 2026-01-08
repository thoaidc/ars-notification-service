package com.ars.notificationservice.service.impl;

import com.ars.notificationservice.constants.ChatConstants;
import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.dto.ChatMessageRequest;
import com.ars.notificationservice.dto.ConversationDTO;
import com.ars.notificationservice.dto.SearchChatMessageRequest;
import com.ars.notificationservice.entity.Notification;
import com.ars.notificationservice.repository.NotificationRepository;
import com.ars.notificationservice.service.NotificationService;

import com.dct.config.common.FileUtils;
import com.dct.model.common.JsonUtils;
import com.dct.model.dto.response.BaseResponseDTO;
import com.dct.model.exception.BaseBadRequestException;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private static final String ENTITY_NAME = "com.ars.notificationservice.service.impl.NotificationServiceImpl";
    private final FileUtils fileUtils = new FileUtils();

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
        this.fileUtils.setPrefixPath(ChatConstants.Upload.PREFIX);
        this.fileUtils.setUploadDirectory(ChatConstants.Upload.LOCATION);
    }

    @Override
    public BaseResponseDTO getAllConversationWithPaging(SearchChatMessageRequest request) {
        if (Objects.isNull(request.getCurrentUserId())) {
            throw new BaseBadRequestException(ENTITY_NAME, "ID người dùng không được bỏ trống.");
        }

        Page<ConversationDTO> conversationDTOPage = notificationRepository.getAllConversationWithPaging(request);
        return BaseResponseDTO.builder().ok(conversationDTOPage.getContent());
    }

    @Override
    public BaseResponseDTO getAllConversationMessageWithPaging(SearchChatMessageRequest request) {
        if (Objects.isNull(request.getCurrentUserId()) || Objects.isNull(request.getPartnerId())) {
            throw new BaseBadRequestException(ENTITY_NAME, "ID người dùng không được bỏ trống.");
        }

        Page<ChatMessageDTO> messageDTOPage = notificationRepository.getAllConversationMessageWithPaging(request);
        List<ChatMessageDTO> messages = messageDTOPage.getContent().stream().peek(messageDTO -> {
            ChatMessageDTO.Message message = JsonUtils.parseJson(messageDTO.getContent(), ChatMessageDTO.Message.class);
            messageDTO.setMessage(message);
        }).toList();
        return BaseResponseDTO.builder().total(messageDTOPage.getTotalElements()).ok(messages);
    }

    @Override
    @Transactional
    public Notification saveMessage(ChatMessageRequest messageDTO) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(messageDTO, notification, "id", "imageFiles");
        notification.setType(ChatConstants.Type.CHAT);
        ChatMessageDTO.Message message = new ChatMessageDTO.Message();

        if (Objects.nonNull(messageDTO.getImageFiles())) {
            List<String> images = fileUtils.autoCompressImageAndSave(messageDTO.getImageFiles());
            message.setImages(images);
        }

        message.setContent(messageDTO.getContent());
        String content = JsonUtils.toJsonString(message);
        notification.setContent(content);
        return notificationRepository.save(notification);
    }
}
