package com.lumen.social.controller.dto.catalog;

import com.lumen.social.domain.video.Video;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record VideoResponse(
    UUID id,
    String title,
    String description,
    boolean premium,
    BigDecimal purchasePriceCredits,
    BigDecimal rentalPriceCredits,
    String authorName,
    Instant createdAt
) {

    public VideoResponse(Video video) {
        this(
                video.getId(),
                video.getTitle(),
                video.getDescription(),
                video.isPremium(),
                video.getPurchasePriceCredits(),
                video.getRentalPriceCredits(),
                video.getAuthor().getName(),
                video.getCreatedAt()
        );
    }
}
