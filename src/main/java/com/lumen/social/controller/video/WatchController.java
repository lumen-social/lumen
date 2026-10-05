package com.lumen.social.controller.video;

import com.lumen.social.controller.dto.video.WatchRequest;
import com.lumen.social.controller.dto.video.WatchResponse;
import com.lumen.social.service.video.VideoAccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("v1/videos/{videoId}/watch")
public class WatchController {

    private final VideoAccessService videoAccessService;

    public WatchController(VideoAccessService videoAccessService) {
        this.videoAccessService = videoAccessService;
    }

    @GetMapping
    public ResponseEntity<WatchResponse> watch(WatchRequest watchRequest){
        String url = videoAccessService.getPlaybackUrl(watchRequest.videoId(), watchRequest.requesterEmail());
        return ResponseEntity.ok(new WatchResponse(url));
    }
}
