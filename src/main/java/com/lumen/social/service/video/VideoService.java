package com.lumen.social.service.video;

import com.lumen.social.controller.dto.catalog.VideoRequest;
import com.lumen.social.controller.dto.catalog.VideoResponse;
import com.lumen.social.domain.video.Video;
import com.lumen.social.domain.social.User;
import com.lumen.social.repository.video.VideoRepository;
import com.lumen.social.repository.social.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideoService implements IVideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    public VideoService(VideoRepository videoRepository, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VideoResponse create(VideoRequest request, String authorEmail) {
        User author = userRepository.findByEmail(authorEmail).orElseThrow(
               () -> new IllegalArgumentException("User with e-mail: " + authorEmail + " does not exists in database.")
        );
        Video video = new Video(
                request.title(),
                request.description(),
                request.url(),
                request.premium(),
                request.priceCredits(),
                author
        );
        videoRepository.save(video);

        return new VideoResponse(video);
    }

    @Override
    public Page<VideoResponse> list(Pageable pageable) {
        return videoRepository.findAllByOrderByCreatedAtDesc(pageable).map(VideoResponse::new);
    }
}
