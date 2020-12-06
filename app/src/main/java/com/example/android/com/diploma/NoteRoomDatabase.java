package com.example.android.com.diploma;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;
import java.util.Date;

@Database(entities = {Note.class}, version = 7, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class NoteRoomDatabase extends RoomDatabase {

    public abstract NoteDAO noteDAO();

    private static NoteRoomDatabase INSTANCE;

    static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "note_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDAO mDao;

        String[] notesTitle = {"Моя первая записка"};
        String[] noteText = {"Привет!"};

        Date currentTime = Calendar.getInstance().getTime();
        Date[] dates = {currentTime};
        PopulateDbAsync(NoteRoomDatabase db) {
            mDao = db.noteDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            if (mDao.getAnyNote().length < 1) {
                for (int i = 0; i <= notesTitle.length - 1; i++) {
                    Note note = new Note(notesTitle[i], noteText[i], dates[i], true);
                    mDao.insert(note);
                }
            }
            return null;
        }
    }
}



