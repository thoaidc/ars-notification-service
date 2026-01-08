package com.ars.notificationservice.entity;

import com.ars.notificationservice.dto.ChatMessageDTO;
import com.ars.notificationservice.dto.ConversationDTO;
import com.dct.config.entity.AbstractAuditingEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.SqlResultSetMappings;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "notification")
@SuppressWarnings("unused")
@SqlResultSetMappings(
    {
        @SqlResultSetMapping(
            name = "chatMessageGetWithPaging",
            classes = {
                @ConstructorResult(
                    targetClass = ChatMessageDTO.class,
                    columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "senderId", type = Integer.class),
                        @ColumnResult(name = "senderName", type = String.class),
                        @ColumnResult(name = "receiverId", type = Integer.class),
                        @ColumnResult(name = "receiverName", type = String.class),
                        @ColumnResult(name = "content", type = String.class),
                        @ColumnResult(name = "createdDate", type = Instant.class)
                    }
                )
            }
        ),
        @SqlResultSetMapping(
            name = "conversationGetWithPaging",
            classes = {
                @ConstructorResult(
                    targetClass = ConversationDTO.class,
                    columns = {
                        @ColumnResult(name = "partnerId", type = Integer.class),
                        @ColumnResult(name = "partnerName", type = String.class),
                        @ColumnResult(name = "latestMessageTime", type = Instant.class)
                    }
                )
            }
        )
    }
)
public class Notification extends AbstractAuditingEntity {
    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 2000, nullable = false)
    private String content;

    @Column(name = "sender_id", nullable = false)
    private Integer senderId;

    @Column(name = "sender_name", nullable = false)
    private String senderName;

    @Column(name = "sender_email")
    private String senderEmail;

    @Column(name = "receiver_id", nullable = false)
    private Integer receiverId;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_email")
    private String receiverEmail;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }
}
