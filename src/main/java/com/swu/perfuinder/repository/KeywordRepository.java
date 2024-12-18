package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.PerfumeKeyword;
import com.swu.perfuinder.dto.keyword.KeywordResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordRepository extends JpaRepository<PerfumeKeyword, Long> {
    List<KeywordResponse.KeywordInfo> findByPerfumeId(@Param("perfumeId") Long perfumeId);
}
