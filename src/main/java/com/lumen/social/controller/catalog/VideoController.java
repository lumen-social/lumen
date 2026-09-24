package com.lumen.social.controller.catalog;

import com.lumen.social.controller.dto.catalog.VideoRequest;
import com.lumen.social.controller.dto.catalog.VideoResponse;
import com.lumen.social.service.catalog.VideoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/videos")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping
    public ResponseEntity<VideoResponse> create(
            @RequestBody @Valid VideoRequest request,
            @RequestHeader("authorEmail") String authorEmail
    ) {
        VideoResponse videoResponse = videoService.create(request, authorEmail);

        // Por enquanto o create não retorna a location.

        return ResponseEntity.status(HttpStatus.CREATED).body(videoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<VideoResponse>> list(Pageable pageable){
        return ResponseEntity.ok(videoService.list(pageable));
    }
}
