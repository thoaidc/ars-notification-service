package com.ars.notificationservice.constants;

public interface ChatConstants {
    interface Upload {
        String PREFIX = "/uploads/notifications/";
        String LOCATION = "opt/uploads/notifications/";
    }

    interface Type {
        Integer CHAT = 1;
        Integer NOTIFICATION_APP = 2;
        Integer EMAIL = 3;
    }
}
