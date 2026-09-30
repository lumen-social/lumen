package com.lumen.social.controller.wallet;

import com.lumen.social.domain.pay.DepositRequestDto;
import com.lumen.social.domain.pay.DepositResponseDto;
import com.lumen.social.domain.pay.Wallet;
import com.lumen.social.domain.social.User;
import com.lumen.social.exception.pay.TransactionInvalidException;
import com.lumen.social.repository.social.user.UserRepository;
import com.lumen.social.service.pay.WalletService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/wallet")
public class WalletController {

    private final WalletService walletService;
    private final UserRepository userRepository;

    WalletController(WalletService walletService, UserRepository userRepository) {
        this.walletService = walletService;
        this.userRepository = userRepository;
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponseDto> deposit(@Valid @RequestBody DepositRequestDto request){
        User user = userRepository.findByEmail(request.userEmail()).orElseThrow();
        return ResponseEntity.ok(walletService.deposit(user.getId(), request.amount(), request.description()));
    }
}
