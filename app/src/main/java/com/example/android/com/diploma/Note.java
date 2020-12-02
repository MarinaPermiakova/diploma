package com.example.android.com.diploma;

import androidx.annotation.NonNull;
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
    private String title;

    @NonNull
    @ColumnInfo(name = "info")
    private String info;

    @NonNull
    @ColumnInfo(name = "deadline")
    private Date deadline;

    @ColumnInfo(name = "hasdeadline")
    private boolean isDeadline;

    Note(String title, String info, Date deadline, boolean isDeadline) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.deadline = deadline;
        this.isDeadline = isDeadline;
    }


    @Ignore
    Note(int id, String title, String info, Date deadline, boolean isDeadline) {
        this.id = id;
        this.title = title;
        this.info = info;
        this.deadline = deadline;
        this.isDeadline = isDeadline;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

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

