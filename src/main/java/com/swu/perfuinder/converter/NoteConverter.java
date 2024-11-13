package com.swu.perfuinder.converter;

import com.swu.perfuinder.domain.Note;
import com.swu.perfuinder.domain.Perfume;
import com.swu.perfuinder.domain.enums.NoteType;
import com.swu.perfuinder.dto.note.NoteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteConverter {
    
    // 메인 노트 리스트로
    public List<String> toMainNoteResponse(List<Note> notes) {
        return notes.stream()
                .filter(note -> note.getNoteType() == NoteType.MAIN)  // MAIN 노트만 필터링
                .map(Note::getNote)  // Note 객체에서 note 문자열만 추출
                .collect(Collectors.toList());
    }

    // 탑 노트 리스트로
    public List<String> toTopNoteResponse(List<Note> notes) {
        return notes.stream()
                .filter(note -> note.getNoteType() == NoteType.TOP)  // TOP 노트만 필터링
                .map(Note::getNote)  // Note 객체에서 note 문자열만 추출
                .collect(Collectors.toList());
    }

    // 미들 노트 리스트로
    public List<String> toMiddleNoteResponse(List<Note> notes) {
        return notes.stream()
                .filter(note -> note.getNoteType() == NoteType.MIDDLE)  // MIDDLE 노트만 필터링
                .map(Note::getNote)  // Note 객체에서 note 문자열만 추출
                .collect(Collectors.toList());
    }

    // 베이스 노트 리스트로
    public List<String> toBaseNoteResponse(List<Note> notes) {
        return notes.stream()
                .filter(note -> note.getNoteType() == NoteType.BASE)  // BASE 노트만 필터링
                .map(Note::getNote)  // Note 객체에서 note 문자열만 추출
                .collect(Collectors.toList());
    }

    // 탑 노트 정보 조회
    public NoteResponse.NoteInfo toNotesInfo (int typeCode, Perfume perfume, List<Note> notes) {

        String desc = "";
        List<String> notesToString = new ArrayList<>();

        switch (typeCode) {
            case 0:
                desc = perfume.getTopDesc();
                notesToString = toTopNoteResponse(notes);
                break;
            case 1:
                desc = perfume.getMiddleDesc();
                notesToString = toMiddleNoteResponse(notes);
                break;
            case 2:
                desc = perfume.getBaseDesc();
                notesToString = toBaseNoteResponse(notes);
        }

        return NoteResponse.NoteInfo.builder()
                .typeCode(typeCode)
                .notes(notesToString)
                .desc(desc)
                .build();
    }
}