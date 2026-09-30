package com.lumen.social.controller.dto.catalog;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record VideoRequest(
    @NotBlank String title,
    @NotBlank String description,
    @NotBlank String url,
    Boolean premium,
    BigDecimal priceCredits
) {
}
