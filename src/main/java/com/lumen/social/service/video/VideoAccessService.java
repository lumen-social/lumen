package com.lumen.social.service.video;

import com.lumen.social.domain.social.User;
import com.lumen.social.domain.video.Video;
import com.lumen.social.exception.social.UserNotFoundException;
import com.lumen.social.exception.video.VideoNotFoundException;
import com.lumen.social.repository.social.user.UserRepository;
import com.lumen.social.repository.video.VideoAccessRepository;
import com.lumen.social.repository.video.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VideoAccessService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final VideoAccessRepository videoAccessRepository;

    public VideoAccessService(VideoRepository videoRepository, UserRepository userRepository, VideoAccessRepository videoAccessRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
        this.videoAccessRepository = videoAccessRepository;
    }

    public String getPlaybackUrl(UUID videoId, String requesterEmail){
        Video video = videoRepository.findById(videoId).orElseThrow(
                () -> new VideoNotFoundException("Video not found with id: " + videoId)
        );
        if(!video.isPremium()) return video.getUrl();

        User requester = userRepository.findByEmail(requesterEmail).orElseThrow(
                () -> new UserNotFoundException("User not found with email: " + requesterEmail)
        );

        userHasAccess(video, requester);
        return video.getUrl();
    }

    private void userHasAccess(Video video, User requester) {
        boolean isAuthor = video.getAuthor().getId() == requester.getId();
        boolean hasAccess = isAuthor || videoAccessRepository.hasActiveAccess(requester.getId(), video.getId());

        if(!hasAccess) {
            throw new SecurityException("You don't have access to this video.");
        }
    }
}
