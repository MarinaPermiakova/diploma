package com.example.android.com.diploma;

import java.util.List;

interface NoteRepositoryInterface {
    Note getNoteById(String id);
    List<Note> getNotes();
    void saveNote(Note note);
    void deleteById(String id);
}
