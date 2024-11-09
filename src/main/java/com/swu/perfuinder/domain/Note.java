package com.swu.perfuinder.domain;

import com.swu.perfuinder.domain.common.BaseEntity;
import com.swu.perfuinder.domain.enums.NoteType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Note extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    @Enumerated(EnumType.STRING)
    private NoteType noteType;

    @Column(length = 100)
    private String note;

    @Builder
    public Note(Perfume perfume, NoteType noteType, String note) {
        this.perfume = perfume;
        this.noteType = noteType;
        this.note = note;
    }

    // 연관관계 편의 메서드
    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
        perfume.getNotes().add(this);
    }
}