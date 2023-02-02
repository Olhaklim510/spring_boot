package com.company.spring_boot.note;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new HashMap<>();
    private static Long notesIdCounter = 1L;

    public synchronized Note add(Note note) {
        note.setId(notesIdCounter++);
        notes.put(note.getId(), note);
        return note;
    }

    public synchronized void deleteById(Long id) {
        if (!notes.containsKey(id)) {
            throw new NullPointerException("This note doesn't exist");
        }
        notes.remove(id);
    }

    public synchronized void update(Note note) {
        if (!notes.containsKey(note.getId())) {
            throw new NullPointerException("This note doesn't exist");
        }
        notes.put(note.getId(), note);
    }

    public synchronized Note getById(Long id) {
        if (!notes.containsKey(id)) {
            throw new NullPointerException("This note doesn't exist");
        }
        return notes.get(id);
    }

    public synchronized Map<Long, Note> listAll() {
        return notes;
    }

    public synchronized List<Note> searchNote(String pattern) {
        return listAll()
                        .values()
                        .stream()
                        .filter(note ->
                                note.getContent().contains(pattern)
                                        || note.getTitle().contains(pattern))
                .toList();
    }
}
