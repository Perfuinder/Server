package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 향수 ID와 디바이스 ID로 찜하기 존재 여부 확인
    boolean existsByPerfumeIdAndDeviceId(Long perfumeId, String deviceId);
}
