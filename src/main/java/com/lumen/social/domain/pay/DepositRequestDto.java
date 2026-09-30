package com.lumen.social.domain.pay;

import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

public record DepositRequestDto(
       String userEmail,
       @Min(0) BigDecimal amount,
       String description
) {
}
