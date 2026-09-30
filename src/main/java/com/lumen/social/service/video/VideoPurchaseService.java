package com.lumen.social.service.video;

import com.lumen.social.domain.social.User;
import com.lumen.social.domain.video.Video;
import com.lumen.social.exception.social.UserNotFoundException;
import com.lumen.social.exception.video.VideoNotFoundException;
import com.lumen.social.repository.social.user.UserRepository;
import com.lumen.social.repository.video.VideoRepository;
import com.lumen.social.service.pay.WalletService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VideoPurchaseService {
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final WalletService walletService;

    public VideoPurchaseService(VideoRepository videoRepository, UserRepository userRepository, WalletService walletService) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    public void unlock(UUID videoId, String buyerEmail){
        Video video = videoRepository.findById(videoId).orElseThrow(
                () -> new VideoNotFoundException("Video with ID: " + videoId + " not found.")
        );
        if(!video.isPremium()) throw new IllegalArgumentException("This video is already free.");
        User buyer = userRepository.findByEmail(buyerEmail).orElseThrow(
                () -> new UserNotFoundException("User with E-mail: " + buyerEmail + " not found.")
        );

        walletService.debit(buyer.getId(), video.getPriceCredits(), "Unlock: " + video.getTitle(), video.getId());
    }
}
