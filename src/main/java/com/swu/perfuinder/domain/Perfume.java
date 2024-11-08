package com.swu.perfuinder.domain;

import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.domain.enums.Gender;
import com.swu.perfuinder.domain.enums.Season;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
}
