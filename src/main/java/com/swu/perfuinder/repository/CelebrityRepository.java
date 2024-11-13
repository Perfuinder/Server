package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Celebrity;
import com.swu.perfuinder.dto.celebrity.CelebrityResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {

    List<CelebrityResponse.CelebrityInfo> findByPerfumeId(@Param("perfumeId") Long perfumeId);
}