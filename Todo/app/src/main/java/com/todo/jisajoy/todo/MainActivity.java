package com.todo.jisajoy.todo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    NoteViewModel noteViewModel;
    public static final int NOTE_EXTRA_RESULT = 1;
    public static final int NOTE_EDIT_EXTRA_RESULT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.fl_add_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNoteActivityIntent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivityForResult(addNoteActivityIntent, NOTE_EXTRA_RESULT);
            }
        });

        RecyclerView noteRecycleView = findViewById(R.id.notes_rv);
        noteRecycleView.setLayoutManager(new LinearLayoutManager(this));
        noteRecycleView.setHasFixedSize(true);

        final NoteAdapter noteAdapter = new NoteAdapter();
        noteRecycleView.setAdapter(noteAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                noteAdapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                noteViewModel.delete(noteAdapter.getNotes(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(noteRecycleView);

        noteAdapter.onItemClickCallerMethod(new NoteAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intentEditNote = new Intent(MainActivity.this, AddNoteActivity.class);
                intentEditNote.putExtra(AddNoteActivity.EXTRA_ID, note.getId());
                intentEditNote.putExtra(AddNoteActivity.EXTRA_TITLE, note.getTitle());
                intentEditNote.putExtra(AddNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                intentEditNote.putExtra(AddNoteActivity.EXTRA_PRIORITY, note.getPriority());
                startActivityForResult(intentEditNote, NOTE_EDIT_EXTRA_RESULT);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NOTE_EXTRA_RESULT && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);


            Note note = new Note(title, description, priority);
            noteViewModel.insert(note);

            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == NOTE_EDIT_EXTRA_RESULT && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddNoteActivity.EXTRA_ID, -1);
            Log.d("mainactivityID", id+"");
            if (id == -1){
                Toast.makeText(this, "Note can't update", Toast.LENGTH_SHORT).show();
                return;
            }
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, priority);
            note.setId(id);
            noteViewModel.update(note);

        } else{
            Toast.makeText(this, "Note Not Saved", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, "All Notes Deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
