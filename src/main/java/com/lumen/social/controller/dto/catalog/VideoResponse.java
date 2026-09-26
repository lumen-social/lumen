package com.lumen.social.controller.dto.catalog;

import com.lumen.social.domain.video.Video;

import java.util.UUID;

public record VideoResponse(
    UUID id,
    String title,
    String description,
    String url,
    boolean premium,
    Integer priceCredits
) {

    public VideoResponse(Video video) {
        this(
                video.getId(),
                video.getTitle(),
                video.getDescription(),
                video.getUrl(), video.isPremium(),
                video.getPriceCredits()
        );
    }
}
