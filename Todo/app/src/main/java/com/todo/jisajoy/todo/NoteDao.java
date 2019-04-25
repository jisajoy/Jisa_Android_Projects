package com.todo.jisajoy.todo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM table_note")
    void deleteAllNotes();

    @Query("SELECT * from table_note order by priority desc")
    LiveData<List<Note>> getAllNotes(); // here live data will help to notify the any update in the table and helps to update automatically

}
