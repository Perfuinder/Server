package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
