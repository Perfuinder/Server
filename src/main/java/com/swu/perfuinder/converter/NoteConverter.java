package com.swu.perfuinder.converter;

import com.swu.perfuinder.domain.Note;
import com.swu.perfuinder.domain.enums.NoteType;
import com.swu.perfuinder.dto.note.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteConverter {
    public List<NoteResponse.NoteInfo> toMainNoteResponse(List<Note> notes) {
        return notes.stream()
                .filter(note -> note.getNoteType() == NoteType.MAIN)
                .map(this::toNoteInfo)
                .collect(Collectors.toList());
    }

    public NoteResponse.NoteInfo toNoteInfo(Note note) {
        return NoteResponse.NoteInfo.builder()
                .noteType(note.getNoteType())
                .note(note.getNote())
                .build();
    }
}