package com.lumen.social.controller.video;

import com.lumen.social.service.video.VideoPurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/videos/{videoId}/unlock")
class VideoPurchaseController {

    private final VideoPurchaseService videoPurchaseService;

    VideoPurchaseController(VideoPurchaseService videoPurchaseService) {
        this.videoPurchaseService = videoPurchaseService;
    }

    @PostMapping
    public ResponseEntity<Void> unlock(@PathVariable UUID videoId, String buyerEmail, @RequestBody AccessType type) {
        // Voltei para buyerEmail no lugar do auth.getPrincipal por enquanto.
        videoPurchaseService.unlock(videoId, buyerEmail, type);
        return ResponseEntity.ok().build();
    }
}
