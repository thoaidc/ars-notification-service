package com.ars.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class ConversationDTO {
    private Integer partnerId;
    private String partnerName;
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "dd-MM-yyyy HH:mm:ss",
        timezone = "UTC"
    )
    private Instant latestMessageTime;

    public ConversationDTO(Integer partnerId, String partnerName, Instant latestMessageTime) {
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.latestMessageTime = latestMessageTime;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public Instant getLatestMessageTime() {
        return latestMessageTime;
    }

    public void setLatestMessageTime(Instant latestMessageTime) {
        this.latestMessageTime = latestMessageTime;
    }
}
