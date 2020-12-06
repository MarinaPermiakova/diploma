package com.example.android.com.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.example.android.com.diploma.MainActivity.EXTRA_DATA_ID;
import static com.example.android.com.diploma.MainActivity.EXTRA_DATA_UPDATE_NOTE_DEADLINE;
import static com.example.android.com.diploma.MainActivity.EXTRA_DATA_UPDATE_NOTE_HASDEADLINE;
import static com.example.android.com.diploma.MainActivity.EXTRA_DATA_UPDATE_NOTE_TEXT;
import static com.example.android.com.diploma.MainActivity.EXTRA_DATA_UPDATE_NOTE_TITLE;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_TITLE = "com.example.android.diploma.REPLY_TITLE";
    public static final String EXTRA_REPLY_TEXT = "com.example.android.diploma.REPLY_TEXT";
    public static final String EXTRA_REPLY_ID = "com.example.android.diploma.REPLY_ID";
    public static final String EXTRA_REPLY_DEADLINE = "com.example.android.diploma.DEADLINE";
    public static final String EXTRA_HAS_DEADLINE = "com.example.android.diploma.HASDEADLINE";

    EditText noteTitle;
    EditText noteText;
    EditText dateTimeOfDeadline;
    ImageView calendar;
    String dateMessage;
    Date date;
    CheckBox checkDeadline;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar__for_detail_activity);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        noteTitle = findViewById(R.id.titleDetail);
        noteText = findViewById(R.id.newsTitleDetail);
        dateTimeOfDeadline = findViewById(R.id.date_time_of_deadline);
        checkDeadline = findViewById(R.id.checkbox);
        calendar = findViewById(R.id.image);

        boolean has_dead_line = getIntent().getBooleanExtra(EXTRA_DATA_UPDATE_NOTE_HASDEADLINE, false);
        if (has_dead_line) {
            checkDeadline.setChecked(true);
            dateTimeOfDeadline.setVisibility(View.VISIBLE);
            calendar.setVisibility(View.VISIBLE);
        }

        checkDeadline.setOnClickListener(view -> {
            if (checkDeadline.isChecked()) {
                dateTimeOfDeadline.setVisibility(View.VISIBLE);
                calendar.setVisibility(View.VISIBLE);
            } else {
                dateTimeOfDeadline.setVisibility(View.INVISIBLE);
                calendar.setVisibility(View.INVISIBLE);
            }
        });

        //int id = -1;
        extras = getIntent().getExtras();

        if (extras != null) {
            setTitle(getString(R.string.edit_mode));
            String title = extras.getString(EXTRA_DATA_UPDATE_NOTE_TITLE, "");
            String text = extras.getString(EXTRA_DATA_UPDATE_NOTE_TEXT, "");
            date = new Date();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            date.setTime(getIntent().getLongExtra(EXTRA_DATA_UPDATE_NOTE_DEADLINE, -1));
            //boolean hasDeadline = extras.getBoolean(EXTRA_DATA_UPDATE_NOTE_HASDEADLINE, false);

            if (!title.isEmpty()) {
                noteTitle.setText(title);
                noteText.setText(text);
                dateTimeOfDeadline.setText(df.format(date));
            }
        }
        calendar.setOnClickListener(this::showDatePicker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_for_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent replyIntent = new Intent();
        if (TextUtils.isEmpty(noteTitle.getText())) {
            setResult(RESULT_CANCELED, replyIntent);
        } else {
            String notesTitleString = noteTitle.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY_TITLE, notesTitleString);

            String infoNote = noteText.getText().toString();
            replyIntent.putExtra(EXTRA_REPLY_TEXT, infoNote);

            replyIntent.putExtra(EXTRA_HAS_DEADLINE, checkDeadline.isChecked());

            Date date2 = new Date();

            if (checkDeadline.isChecked()) {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                try {
                    date2 = df.parse(dateTimeOfDeadline.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                date2 = Calendar.getInstance().getTime();
            }
            assert date2 != null;
            replyIntent.putExtra(EXTRA_REPLY_DEADLINE, date2.getTime());

            if (extras != null && extras.containsKey(EXTRA_DATA_ID)) {
                int id = extras.getInt(EXTRA_DATA_ID, -1);
                if (id != -1) {
                    replyIntent.putExtra(EXTRA_REPLY_ID, id);
                }
            }

            setResult(RESULT_OK, replyIntent);
        }
        finish();
        return true;
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        dateMessage = (month_string + "/" + day_string + "/" + year_string);
        dateTimeOfDeadline.setText(dateMessage);
    }
}
