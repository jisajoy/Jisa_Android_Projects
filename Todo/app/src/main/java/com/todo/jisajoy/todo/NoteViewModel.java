package com.todo.jisajoy.todo;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    //here the constructor have application rather than application context is because viewmodel should be decoupled from activity life cycle
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void insert(Note note){
        noteRepository.insert(note);
    }

    public void update(Note note){
        noteRepository.update(note);
    }

    public void delete(Note note){
        noteRepository.delete(note);
    }

    public void deleteAllNotes(){
        noteRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
