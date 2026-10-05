package com.lumen.social.domain.video;

import com.lumen.social.domain.social.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "video")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(nullable = false)
    private String url;

    @Column(name = "is_premium", nullable = false)
    private boolean isPremium;

    @Column(name = "purchase_price_credits")
    private BigDecimal purchasePriceCredits;

    @Column(name = "rental_price_credits")
    private BigDecimal rentalPriceCredits;

    @Column(name = "rental_duration_hours")
    private Integer rentalDurantionHours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    protected void atCreation() {
        this.createdAt = Instant.now();
    }

    public Video(String title, String description, String url, boolean isPremium, BigDecimal purchasePriceCredits, BigDecimal rentalPriceCredits, Integer rentalDurantionHours, User author) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.isPremium = isPremium;
        this.purchasePriceCredits = purchasePriceCredits;
        this.rentalPriceCredits = rentalPriceCredits;
        this.rentalDurantionHours = rentalDurantionHours;
        this.author = author;
    }
}
