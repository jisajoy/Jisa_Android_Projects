package com.todo.jisajoy.todo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {
    private LiveData<List<Note>> allNotes;
    NoteDao noteDao;

    public NoteRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getNoteDatabaseInstance(application);// calling a static method
        noteDao = noteDatabase.noteDao();//abstract method is called because i already have the note database instance
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note) {
        new insertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note) {
        new updateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note) {
        new deleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new deleteAllNoteAsyncTask(noteDao).execute();
    }

public LiveData<List<Note>> getAllNotes(){
        return allNotes;
}
    private static class insertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        private insertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }


    private static class updateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        private updateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }


    private static class deleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao;

        private deleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }


    private static class deleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;

        private deleteAllNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
