package com.lumen.social.exception.video;

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VideoNotFoundException(String message) {
        super(message);
    }
}
