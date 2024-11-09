package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Volume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolumeRepository extends JpaRepository<Volume, Long> {
}
