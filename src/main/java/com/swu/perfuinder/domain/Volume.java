package com.swu.perfuinder.domain;

import com.swu.perfuinder.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Volume extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    @Enumerated(EnumType.STRING)
    private com.swu.perfuinder.domain.enums.Volume volume;

    private Integer price;

    @Builder
    public Volume(Perfume perfume, com.swu.perfuinder.domain.enums.Volume volume, Integer price) {
        this.perfume = perfume;
        this.volume = volume;
        this.price = price;
    }

    // 연관관계 편의 메서드
    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
        perfume.getVolumes().add(this);
    }
}
