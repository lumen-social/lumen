package com.lumen.social.domain.pay;

import java.math.BigDecimal;
import java.util.UUID;

public record DepositResponseDto(
        UUID id,
        UUID ownerId,
        BigDecimal balance
) {
    public DepositResponseDto(Wallet wallet) {
        this(wallet.getId(), wallet.getOwner().getId(), wallet.getBalance());
    }
}
