package com.jtrio.zagzag.utils;

import com.jtrio.zagzag.enums.MessageStatus;

public class ContentUtils {
    public static String setCommentDto(Boolean secret, MessageStatus status, String comment) {
        String result = secret ? null : comment;

        result = status.getName() == status.NORMAL.getName() ? result : null;
        switch (status) {
            case DELETE:
                result = status.DELETE.getName();
                break;
            case BLOCK:
                result = status.BLOCK.getName();
                break;
            default:
                break;
        }

        return result;
    }
}

