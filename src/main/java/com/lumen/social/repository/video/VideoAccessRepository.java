package com.lumen.social.repository.video;

import com.lumen.social.domain.video.VideoAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoAccessRepository extends JpaRepository<VideoAccess, Long> {
    Optional<VideoAccess> findByUserIdAndVideoId(UUID userId, UUID videoId);

    @Query("SELECT COUNT(va) > 0 FROM VideoAccess va WHERE va.user.id = :userId AND va.video.id = :videoId " +
    "AND (va.expiresAt IS NULL OR va.expiresAt > CURRENT_TIMESTAMP)")
    boolean hasActiveAccess(@Param("userId") UUID userId, @Param("videoId") UUID videoId);
}
