package com.swu.perfuinder.domain;

import com.swu.perfuinder.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

//    @Column(length = 100)
//    private String deviceId;

    // 연관관계 편의 메서드
    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
        perfume.getFavorites().add(this);
    }

}
