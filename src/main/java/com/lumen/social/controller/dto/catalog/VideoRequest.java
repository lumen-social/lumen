package com.lumen.social.controller.dto.catalog;

import jakarta.validation.constraints.NotBlank;

public record VideoRequest(
    @NotBlank String title,
    String description,
    @NotBlank String url,
    Boolean premium,
    Integer priceCredits
) {
}
