package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Note;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Log> {
}
