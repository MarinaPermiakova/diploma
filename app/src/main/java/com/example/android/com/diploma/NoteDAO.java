package com.example.android.com.diploma;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Update
    void update(Note... note);

    @Delete
    void deleteNote(Note note);

    @Query("SELECT * from note_table ORDER BY hasdeadline ASC, deadline ASC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * from note_table LIMIT 1")
    Note[] getAnyNote();
}
