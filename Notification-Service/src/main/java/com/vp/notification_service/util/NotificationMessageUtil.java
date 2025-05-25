package com.vp.notification_service.util;


import com.vp.notification_service.model.enums.NotificationType;

public class NotificationMessageUtil {

    private NotificationMessageUtil() {
        // Prevent instantiation
    }

    // Utility method for readable messages
    public static String buildReadableMessage(String title, NotificationType type) {
        String action = capitalize(type.name().toLowerCase()); // e.g., "Updated"
        return String.format("Content '%s' was %s.", title, action);
    }

    // Capitalize first letter
    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}