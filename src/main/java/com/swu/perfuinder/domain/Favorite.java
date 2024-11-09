package com.swu.perfuinder.domain;

import com.swu.perfuinder.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    @Column(length = 100)
    private String deviceId;

    @Builder
    public Favorite(Perfume perfume, String deviceId) {
        this.perfume = perfume;
        this.deviceId = deviceId;
    }

    // 연관관계 편의 메서드
    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
        perfume.getFavorites().add(this);
    }

}
