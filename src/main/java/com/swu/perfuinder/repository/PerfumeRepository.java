package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.domain.enums.Brand;
import com.swu.perfuinder.domain.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    // Native Query 사용
    @Query(value = "SELECT * FROM perfume p WHERE p.season = :season ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Perfume> findRandomBySeason(@Param("season") String season, @Param("limit") int limit);

    @Query(value = "SELECT * FROM perfume p WHERE p.brand = :brand ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Perfume> findRandomByBrand(@Param("brand") String brand, @Param("limit") int limit);
}