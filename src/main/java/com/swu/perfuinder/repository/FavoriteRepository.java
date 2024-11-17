package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    // 향수 ID와 디바이스 ID로 찜하기 존재 여부 확인
    //boolean existsByPerfumeIdAndDeviceId(Long perfumeId, String deviceId);

    // 향수 ID로만 찜하기 존재 여부 확인
    boolean existsByPerfumeId(Long perfumeId);

    // 찜하기 상태 변경을 위한 향수 아이디로 조회, Optional
    Optional<Favorite> findByPerfumeId(Long perfumeId);

}
