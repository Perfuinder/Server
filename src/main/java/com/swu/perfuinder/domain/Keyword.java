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
public class Keyword extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    @Enumerated(EnumType.STRING)
    private com.swu.perfuinder.domain.enums.Keyword keyword;

    @Builder
    public Keyword(Perfume perfume, com.swu.perfuinder.domain.enums.Keyword keyword) {
        this.perfume = perfume;
        this.keyword = keyword;
    }

    // 연관관계 편의 메서드
    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
        perfume.getKeywords().add(this);
    }
}