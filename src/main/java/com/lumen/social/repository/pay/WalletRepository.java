package com.lumen.social.repository.pay;

import com.lumen.social.domain.pay.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    Optional<Wallet> findWalletByOwnerId(UUID id);
}
