package com.company.spring_boot.note;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new HashMap<>();
    private final Long counter;
    private Long notesIdCounter = 1L;

    public NoteService() {
        this.counter = notesIdCounter++;
    }

    public Note add(Note note) {
        note.setId(counter);
        notes.put(note.getId(), note);
        return note;
    }

    public void deleteById(Long id) {
        if (!notes.containsKey(id)) {
            throw new NullPointerException("This note doesn't exist");
        }
        notes.remove(id);
    }

    public void update(Note note) {
        if (!notes.containsKey(note.getId())) {
            throw new NullPointerException("This note doesn't exist");
        }
       notes.put(note.getId(),note);
    }

    public Note getById(Long id) {
        if (!notes.containsKey(id)) {
            throw new NullPointerException("This note doesn't exist");
        }
        return notes.get(id);
    }

    public Map<Long, Note> listAll() {
        return notes;
    }

//    public static void main(String[] args) {
//        Note note1 = new Note();
//        note1.setTitle("Test1");
//        note1.setContent("Test1 content");
//        Note note2 = new Note();
//        note2.setTitle("Test2");
//        note2.setContent("Test2 content");
//        Note note3 = new Note();
//        note3.setTitle("Test3");
//        note3.setContent("Test3 content");
//        new NoteService().add(note1);
//        new NoteService().add(note2);
//        new NoteService().add(note3);
//        System.out.println("AFTER ADD");
//        System.out.println("notes = " + notes);
//        System.out.println();
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
