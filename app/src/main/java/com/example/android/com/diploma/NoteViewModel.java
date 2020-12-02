package com.example.android.com.diploma;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepo mRepository;

    private LiveData<List<Note>> mAllWords;

    public NoteViewModel (Application application) {
        super(application);
        mRepository = new NoteRepo(application);
        mAllWords = mRepository.getAllNotes();
    }

    LiveData<List<Note>> getAllWords() { return mAllWords; }

    public void insert(Note note) { mRepository.insert(note); }

    public void update (Note note) { mRepository.update(note); }

    public void deleteNote(Note note) {
        mRepository.deleteNote(note);
    }
}
