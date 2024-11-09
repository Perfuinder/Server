package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfumeRepository extends JpaRepository<Perfume, Long> {
}
