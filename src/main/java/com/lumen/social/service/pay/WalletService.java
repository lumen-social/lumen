package com.lumen.social.service.pay;

import com.lumen.social.domain.pay.DepositResponseDto;
import com.lumen.social.domain.pay.Transaction;
import com.lumen.social.domain.pay.TransactionType;
import com.lumen.social.domain.pay.Wallet;
import com.lumen.social.exception.pay.TransactionInvalidException;
import com.lumen.social.exception.pay.WalletNotFoundException;
import com.lumen.social.repository.pay.TransactionRepository;
import com.lumen.social.repository.pay.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class WalletService implements IWalletService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public DepositResponseDto deposit(UUID userId, BigDecimal amount, String description) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0) throw new TransactionInvalidException("Transaction with amount less or equal than 0.");
        Wallet wallet = walletRepository.findWalletByOwnerId(userId).orElseThrow(
                () -> new WalletNotFoundException("Wallet with owner ID: " + userId + " not found.")
        );
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
        saveTransaction(amount, description, TransactionType.DEPOSIT, wallet, null);

        return new DepositResponseDto(wallet);
    }

    @Override
    public Wallet debit(UUID userId, BigDecimal amount, String description, UUID relatedVideoId) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0) throw new TransactionInvalidException("Transaction with amount less or equal than 0.");
        Wallet wallet = walletRepository.findWalletByOwnerId(userId).orElseThrow(
                () -> new WalletNotFoundException("Wallet with owner ID: " + userId + " not found.")
        );
        if(wallet.getBalance().compareTo(amount) < 0) throw new TransactionInvalidException("Insufficient Balance.");

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
        saveTransaction(amount, description, TransactionType.DEBIT, wallet, relatedVideoId);

        return wallet;
    }

    private void saveTransaction(BigDecimal amount,  String description, TransactionType type, Wallet wallet, UUID relatedVideoId) {
        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setType(type);
        transaction.setRelatedVideoId(relatedVideoId);
        transactionRepository.save(transaction);
    }
}
