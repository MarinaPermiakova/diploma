package com.example.android.com.diploma;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepo {

    private NoteDAO noteDAO;
    private LiveData<List<Note>> mAllNotes;

    NoteRepo(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);
        noteDAO = db.noteDAO();
        mAllNotes = noteDAO.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() {
        return mAllNotes;
    }

    public void insert (Note note) {
        new insertAsyncTask(noteDAO).execute(note);
    }

    public void update (Note note) {
        new updateAsyncTask(noteDAO).execute(note);
    }

    public void deleteNote(Note note)  {
        new deleteNotedAsyncTask(noteDAO).execute(note);
    }

    private static class insertAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO mAsyncTaskDao;

        insertAsyncTask(NoteDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDAO mAsyncTaskDao;

        updateAsyncTask(NoteDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteNotedAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO mAsyncTaskDao;

        deleteNotedAsyncTask(NoteDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Note... params) {
            mAsyncTaskDao.deleteNote(params[0]);
            return null;
        }
    }
}
