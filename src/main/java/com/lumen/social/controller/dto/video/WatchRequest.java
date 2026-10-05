package com.lumen.social.controller.dto.video;

import java.util.UUID;

public record WatchRequest(
        UUID videoId,
        String requesterEmail
) {
}
