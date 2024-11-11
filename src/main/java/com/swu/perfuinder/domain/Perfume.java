package com.swu.perfuinder.domain;

import com.swu.perfuinder.domain.common.BaseEntity;
import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.domain.enums.Gender;
import com.swu.perfuinder.domain.enums.Season;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Perfume extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 100)
    private Brand brand;

    @Column(length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Season season;

    @Column(columnDefinition = "TEXT")
    private String topDesc;

    @Column(columnDefinition = "TEXT")
    private String middleDesc;

    @Column(columnDefinition = "TEXT")
    private String baseDesc;

    // 연관관계 추가
    @OneToMany(mappedBy = "perfume", cascade = CascadeType.ALL)
    private List<Volume> volumes = new ArrayList<>();

    @OneToMany(mappedBy = "perfume", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

    @OneToMany(mappedBy = "perfume", cascade = CascadeType.ALL)
    private List<Keyword> keywords = new ArrayList<>();

    @OneToMany(mappedBy = "perfume", cascade = CascadeType.ALL)
    private List<Celebrity> celebrities = new ArrayList<>();

    @OneToMany(mappedBy = "perfume", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    // 연관관계 편의 메서드들 추가
    public void addVolume(Volume volume) {
        volumes.add(volume);
        volume.setPerfume(this);
    }

    public void addNote(Note note) {
        notes.add(note);
        note.setPerfume(this);
    }

    public void addKeyword(Keyword keyword) {
        keywords.add(keyword);
        keyword.setPerfume(this);
    }

    public void addCelebrity(Celebrity celebrity) {
        celebrities.add(celebrity);
        celebrity.setPerfume(this);
    }

    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
        favorite.setPerfume(this);
    }

    @Builder
    public Perfume(Brand brand, String name, String description, String imageUrl,
                   Gender gender, Season season, String topDesc, String middleDesc, String baseDesc) {
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.gender = gender;
        this.season = season;
        this.topDesc = topDesc;
        this.middleDesc = middleDesc;
        this.baseDesc = baseDesc;
    }
}
