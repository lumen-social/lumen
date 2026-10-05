package com.lumen.social.service.video;

import com.lumen.social.controller.video.AccessType;
import com.lumen.social.domain.video.VideoAccess;
import com.lumen.social.domain.social.User;
import com.lumen.social.domain.video.Video;
import com.lumen.social.exception.social.UserNotFoundException;
import com.lumen.social.exception.video.VideoNotFoundException;
import com.lumen.social.repository.social.user.UserRepository;
import com.lumen.social.repository.video.VideoAccessRepository;
import com.lumen.social.repository.video.VideoRepository;
import com.lumen.social.service.pay.IWalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class VideoPurchaseService {
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final VideoAccessRepository videoAccessRepository;
    private final IWalletService walletService;

    public VideoPurchaseService(
            VideoRepository videoRepository, 
            UserRepository userRepository, 
            VideoAccessRepository videoAccessRepository, 
            IWalletService walletService
    ) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.videoAccessRepository = videoAccessRepository;
        this.walletService = walletService;
    }

    public void unlock(UUID videoId, String buyerEmail, AccessType type){
        Video video = videoRepository.findById(videoId).orElseThrow(
                () -> new VideoNotFoundException("Video with ID: " + videoId + " not found.")
        );
        if(!video.isPremium()) throw new IllegalArgumentException("This video is already free.");
        User buyer = userRepository.findByEmail(buyerEmail).orElseThrow(
                () -> new UserNotFoundException("User with E-mail: " + buyerEmail + " not found.")
        );

        if (debit(type, buyer, video)) return;

        persistVideoAccess(type, buyer, video);
    }

    private boolean debit(AccessType type, User buyer, Video video) {
        if(videoAccessRepository.hasActiveAccess(buyer.getId(), video.getId())) return true;

        BigDecimal price = type == AccessType.RENTAL ? video.getRentalPriceCredits() : video.getPurchasePriceCredits();

        walletService.debit(buyer.getId(), price, "Unlock: " + video.getTitle(), video.getId());
        return false;
    }

    private void persistVideoAccess(AccessType type, User buyer, Video video) {
        VideoAccess videoAccess = videoAccessRepository.findByUserIdAndVideoId(buyer.getId(), video.getId())
                .orElseGet(VideoAccess::new);

        videoAccess.setUser(buyer);
        videoAccess.setVideo(video);
        videoAccess.setExpiresAt(type == AccessType.RENTAL
            ? Instant.now().plus(video.getRentalDurantionHours(), ChronoUnit.HOURS)
            : null);

        videoAccessRepository.save(videoAccess);
    }
}
