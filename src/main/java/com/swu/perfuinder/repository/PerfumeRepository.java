package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.domain.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    // Native Query 사용
    // 계절로 향수 조회
    @Query(value = "SELECT * FROM perfume p WHERE p.season = :season ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Perfume> findRandomBySeason(@Param("season") String season, @Param("limit") int limit);

    // 브랜드로 향수 조회
    @Query(value = "SELECT * FROM perfume p WHERE p.brand = :brand ORDER BY RAND()", nativeQuery = true)
    List<Perfume> findRandomByBrand(@Param("brand") String brand);

    // 브랜드와 이름을 합쳐서 향수 조회: Gemini용
    Optional<Perfume> findByBrandAndName(Brand brand, String name);

    // 향수 아이디로 조회
    Optional<Perfume> findById(@Param("perfumeId") Long perfumeId);

    // 향수 비교 선택 추천을 위한 아이디들로 조회
    @Query("SELECT DISTINCT p FROM Perfume p " +
            "LEFT JOIN FETCH p.notes n " +
            "WHERE p.id IN :perfumeIds")
    List<Perfume> findByPerfumeIds(@Param("perfumeIds") List<Long> perfumeIds);

    // 일반 검색 중 브랜드 검색 - 정확한 enum 값으로 검색
    // 기본 정보와 notes만 fetch join
    @Query("SELECT DISTINCT p FROM Perfume p " +
            "LEFT JOIN FETCH p.notes " +
            "WHERE p.brand = :brand")
    List<Perfume> findByBrand(@Param("brand") Brand brand);

    // volume 정보는 별도로 조회
    @Query("SELECT DISTINCT p FROM Perfume p " +
            "LEFT JOIN FETCH p.volumes " +
            "WHERE p IN :perfumes")
    List<Perfume> findVolumesForPerfumes(@Param("perfumes") List<Perfume> perfumes);

    // 일반 검색 중 제품명 검색 - 포함 검색
    // 기본 정보와 notes만 fetch join (제품명 검색)
    @Query("SELECT DISTINCT p FROM Perfume p " +
            "LEFT JOIN FETCH p.notes " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Perfume> findByNameContaining(@Param("query") String query);

    //찜한 향수 리스트 조회
    @Query("SELECT DISTINCT p FROM Perfume p " +
            "LEFT JOIN FETCH p.keywords k " +  // keywords 정보 함께 조회
            "JOIN p.favorites f " +            // 찜한 향수만 필터링
            "WHERE f.id IS NOT NULL")
    List<Perfume> findAllByFavorites();
}