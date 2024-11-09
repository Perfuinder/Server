package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelebrityRepository extends JpaRepository<Celebrity, Long> {
}
