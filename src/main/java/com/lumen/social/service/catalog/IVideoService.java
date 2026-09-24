package com.lumen.social.service.catalog;

import com.lumen.social.controller.dto.catalog.VideoRequest;
import com.lumen.social.controller.dto.catalog.VideoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVideoService {
    VideoResponse create(VideoRequest request, String authorEmail);
    Page<VideoResponse> list(Pageable pageable);
}
