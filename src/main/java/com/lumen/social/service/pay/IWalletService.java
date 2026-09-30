package com.lumen.social.service.pay;

import com.lumen.social.domain.pay.DepositResponseDto;
import com.lumen.social.domain.pay.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public interface IWalletService {
    DepositResponseDto deposit(UUID userId, BigDecimal amount, String description);
    Wallet debit(UUID userId, BigDecimal amount, String description, UUID relatedVideoId);
}
