package com.swu.perfuinder.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.swu.perfuinder.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Celebrity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String url;

    @Builder
    public Celebrity(Perfume perfume, String name, String url) {
        this.perfume = perfume;
        this.name = name;
        this.url = url;
    }

    // 연관관계 편의 메서드
    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
        perfume.getCelebrities().add(this);
    }
}