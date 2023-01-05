package com.company.spring_boot.note;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;


class NoteServiceTest {
    NoteService noteService;

    @BeforeEach
    public void beforeEach() {
        noteService=new NoteService();
    }

    @Test
    void ThatAddHandledCorrectly() {
        Note noteAdd=new Note();
        noteAdd.setTitle("TestNoteAdd");
        noteAdd.setContent("TestNoteContentAdd");
        noteService.add(noteAdd);
        Long id= noteAdd.getId();

        Assertions.assertEquals(noteAdd,noteService.getById(id));
    }

    @Test
    void ThatDeleteByIdHandledCorrectly() {
        Note noteDelete=new Note();
        noteDelete.setTitle("TestNoteDelete");
        noteDelete.setContent("TestNoteContentDelete");
        noteService.add(noteDelete);
        Long id= noteDelete.getId();
        noteService.deleteById(id);

        Assertions.assertThrows(NullPointerException.class, () ->noteService.getById(id));
    }

    @Test
    void ThatUpdateHandledCorrectly() {
        Note noteForUpdate=new Note();
        noteForUpdate.setTitle("TestNoteUpdate");
        noteForUpdate.setContent("TestNoteContentUpdate");
        noteService.add(noteForUpdate);
        Long id= noteForUpdate.getId();
        Note noteUpdate=new Note();
        noteUpdate.setId(id);
        noteUpdate.setTitle("TestNoteUpdateNew");
        noteUpdate.setContent("TestNoteContentUpdateNew");
        noteService.update(noteUpdate);

        Assertions.assertEquals(noteUpdate, noteService.getById(id));
    }

    @Test
    void ThatGetByIdHandledCorrectly() {
        Note noteGetById=new Note();
        noteGetById.setTitle("TestNoteGetById");
        noteGetById.setContent("TestNoteContentGetById");
        noteService.add(noteGetById);
        Long id= noteGetById.getId();

        Assertions.assertEquals(noteGetById,noteService.getById(id));

    }

    @Test
    void ThatListAllHandledCorrectly() {
        Map <Long,Note> allNotes=new HashMap<>();
        Note note1FromList=new Note();
        note1FromList.setTitle("TestNote1FromList");
        note1FromList.setContent("TestNote1FromListContent");
        noteService.add(note1FromList);
        Long id1= note1FromList.getId();

        Note note2FromList=new Note();
        note2FromList.setTitle("TestNote2FromList");
        note2FromList.setContent("TestNote2FromListContent");
        noteService.add(note2FromList);
        Long id2= note2FromList.getId();

        Note note3FromList = new Note();
        note3FromList.setTitle("TestNote3FromList");
        note3FromList.setContent("TestNote3FromListContent");
        noteService.add(note3FromList);
        Long id3= note3FromList.getId();

        allNotes.put(id1,note1FromList);
        allNotes.put(id2,note2FromList);
        allNotes.put(id3,note3FromList);

        Assertions.assertEquals(allNotes, noteService.listAll());
    }
}