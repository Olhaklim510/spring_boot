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

    //@PostConstruct
//    public void main() {
//        Note note1 = new Note();
//        note1.setTitle("Test1");
//        note1.setContent("Test1 content");
//        Note note2 = new Note();
//        note2.setTitle("Test2");
//        note2.setContent("Test2 content");
//        Note note3 = new Note();
//        note3.setTitle("Test3");
//        note3.setContent("Test3 content");
//        add(note1);
//        add(note2);
//        add(note3);
//        System.out.println("AFTER ADD");
//        System.out.println("notes = " + notes);
//        System.out.println(notes);
//
//
//        System.out.println("AFTER DELETE");
//        new NoteService().deleteById(3L);
//        System.out.println("notes = " + notes);
//        System.out.println();
//
//        System.out.println("AFTER GET_BY_ID");
//        System.out.println("new NoteService().getById(2) = " + new NoteService().getById(2L));
//        System.out.println();
//
//        System.out.println("AFTER UPDATE");
//        Note noteUpdate = new Note();
//        noteUpdate.setId(2L);
//        noteUpdate.setTitle("TestForUpdate");
//        noteUpdate.setContent("TestForUpdate content");
//        new NoteService().update(noteUpdate);
//        System.out.println("notes = " + notes);
//        System.out.println();
//
//        System.out.println("GET ALL_NOTES");
//        System.out.println("notes = " + new NoteService().listAll());
//    }
}
