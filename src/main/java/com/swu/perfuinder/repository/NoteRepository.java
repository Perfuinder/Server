package com.swu.perfuinder.repository;

import com.swu.perfuinder.domain.Note;
import com.swu.perfuinder.domain.enums.NoteType;
import com.swu.perfuinder.dto.note.NoteResponse;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Log> {

    List<NoteResponse.NoteInfo> findByPerfumeIdAndNoteType(
            @Param("perfumeId") Long perfumeId,
            @Param("noteType") NoteType noteType
    );
}
