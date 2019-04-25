package com.todo.jisajoy.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.todo.jisajoy.todo.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.todo.jisajoy.todo.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.todo.jisajoy.todo.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.todo.jisajoy.todo.EXTRA_PRIORITY";

    private EditText mEditTextTitle;
    private EditText mEditTextDescription;
    private NumberPicker mNumberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mEditTextTitle = findViewById(R.id.edit_title);
        mEditTextDescription = findViewById(R.id.edit_description);
        mNumberPicker = findViewById(R.id.number_picker);
        mNumberPicker.setMinValue(1);
        mNumberPicker.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("EDIT NOTE");
            mEditTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            mEditTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            mNumberPicker.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else {
            setTitle("ADD NOTE");
        }
    }

    private void saveNote() {

        String titleText = mEditTextTitle.getText().toString();
        String descriptionText = mEditTextDescription.getText().toString();
        int priority = mNumberPicker.getValue();

        if (titleText.trim().isEmpty() || descriptionText.trim().isEmpty()) {
            Toast.makeText(this, "Please enter a valid text", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, titleText);
        intent.putExtra(EXTRA_DESCRIPTION, descriptionText);
        intent.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        Log.d("AddnoteID", id+"");
        if (id != -1) {
            intent.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_menu_item:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
