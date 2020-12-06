package com.example.android.com.diploma;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final NoteRepository mRepository;

    private final LiveData<List<Note>> mAllNotes;

    public NoteViewModel (Application application) {
        super(application);
        mRepository = new NoteRepository(application);
        mAllNotes = mRepository.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() { return mAllNotes; }

    public void insert(Note note) { mRepository.insert(note); }

    public void update (Note note) { mRepository.update(note); }

    public void deleteNote(Note note) {
        mRepository.deleteNote(note);
    }
}
