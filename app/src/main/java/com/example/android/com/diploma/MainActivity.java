package com.example.android.com.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel mNoteViewModel;

    public static final int NEW_NOTE_ACTIVITY_REQUEST_CODE = 1;
    public static final int UPDATE_NOTE_ACTIVITY_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_UPDATE_NOTE_TITLE = "extra_note_title_to_be_updated";
    public static final String EXTRA_DATA_UPDATE_NOTE_TEXT = "extra_note_text_to_be_updated";
    public static final String EXTRA_DATA_UPDATE_NOTE_DEADLINE = "extra_note_deadline_to_be_updated";
    public static final String EXTRA_DATA_UPDATE_NOTE_HASDEADLINE = "extra_note_hasdeadline_to_be_updated";
    public static final String EXTRA_DATA_ID = "extra_data_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final NoteListAdapter adapter = new NoteListAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        mNoteViewModel.getAllNotes().observe(this, adapter::setNotes);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE);
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }


                    @Override
                    public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        // remove this item from the adapter
                        new AlertDialog.Builder(viewHolder.itemView.getContext())
                                .setMessage("Вы хотите удалить записку?")
                                .setPositiveButton("Да", (dialog, id) -> {
                                    // position of the item to be deleted
                                    int position = viewHolder.getAdapterPosition();
                                    Note myNote = adapter.getNoteAtPosition(position);
                                    mNoteViewModel.deleteNote(myNote);
                                    Toast.makeText(MainActivity.this, "Удалено: " +
                                            myNote.getTitle(), Toast.LENGTH_LONG).show();
                                })
                                .setNegativeButton("Нет", (dialog, id) -> {
                                    // User cancelled the dialog,
                                    // so we will refresh the adapter to prevent hiding the item from UI
                                    adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                                })
                                .create()
                                .show();
                    }
                });

        helper.attachToRecyclerView(mRecyclerView);

        adapter.setOnItemClickListener((v, position) -> {
            Note note = adapter.getNoteAtPosition(position);
            launchUpdateNoteActivity(note);
        });
    }

    private void launchUpdateNoteActivity(Note note) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra(EXTRA_DATA_UPDATE_NOTE_TITLE, note.getTitle());
        detailIntent.putExtra(EXTRA_DATA_UPDATE_NOTE_TEXT, note.getInfo());
        detailIntent.putExtra(EXTRA_DATA_UPDATE_NOTE_DEADLINE, note.getDeadline().getTime());
        detailIntent.putExtra(EXTRA_DATA_UPDATE_NOTE_HASDEADLINE, note.getIsDeadline());
        detailIntent.putExtra(EXTRA_DATA_ID, note.getId());
        startActivityForResult(detailIntent, UPDATE_NOTE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            Date date = new Date();

            date.setTime(data.getLongExtra(DetailActivity.EXTRA_REPLY_DEADLINE, -1));
            Note note = new Note(Objects.requireNonNull(data.getStringExtra(DetailActivity.EXTRA_REPLY_TITLE)), data.getStringExtra(DetailActivity.EXTRA_REPLY_TEXT),
                    date, data.getBooleanExtra(DetailActivity.EXTRA_HAS_DEADLINE, false));
            mNoteViewModel.insert(note);
        } else if (requestCode == UPDATE_NOTE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(DetailActivity.EXTRA_REPLY_ID, -1);
            Date date = new Date();
            date.setTime(data.getLongExtra(DetailActivity.EXTRA_REPLY_DEADLINE, -1));
            String note_title = data.getStringExtra(DetailActivity.EXTRA_REPLY_TITLE);
            String note_text = data.getStringExtra(DetailActivity.EXTRA_REPLY_TEXT);
            boolean has_deadline = data.getBooleanExtra(DetailActivity.EXTRA_HAS_DEADLINE, false);

            if (id != -1) {
                assert note_title != null;
                mNoteViewModel.update(new Note(id, note_title, note_text, date, has_deadline));
            } else {
                Toast.makeText(this, R.string.empty_not_saved,
                        Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}