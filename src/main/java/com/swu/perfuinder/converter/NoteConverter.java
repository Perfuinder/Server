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
    public List<String> toMainNoteResponse(List<Note> notes) {
        return notes.stream()
                .filter(note -> note.getNoteType() == NoteType.MAIN)  // MAIN 노트만 필터링
                .map(Note::getNote)  // Note 객체에서 note 문자열만 추출
                .collect(Collectors.toList());
    }

    public NoteResponse.NoteInfo toNoteInfo(Note note) {
        return NoteResponse.NoteInfo.builder()
                .noteType(note.getNoteType())
                .note(note.getNote())
                .build();
    }
}