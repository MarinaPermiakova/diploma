package com.example.android.com.diploma;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "note_table")
class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private final String title;

    @NonNull
    @ColumnInfo(name = "info")
    private final String info;

    @NonNull
    @ColumnInfo(name = "deadline")
    private final Date deadline;

    @ColumnInfo(name = "hasdeadline")
    private final boolean isDeadline;

    Note(@NonNull String title, @Nullable String info, @Nullable Date deadline, boolean isDeadline) {
        this.title = title;
        assert info != null;
        this.info = info;
        assert deadline != null;
        this.deadline = deadline;
        this.isDeadline = isDeadline;
    }

    @Ignore
    Note(int id, @NonNull String title, @Nullable String info, @Nullable Date deadline, boolean isDeadline) {
        this.id = id;
        this.title = title;
        assert info != null;
        this.info = info;
        assert deadline != null;
        this.deadline = deadline;
        this.isDeadline = isDeadline;
    }

    @NonNull
    String getTitle() {
        return title;
    }

    @NonNull
    String getInfo() {
        return info;
    }

    @NonNull
    Date getDeadline (){
        return deadline;
    }

    boolean getIsDeadline () {
        return isDeadline;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }
}

