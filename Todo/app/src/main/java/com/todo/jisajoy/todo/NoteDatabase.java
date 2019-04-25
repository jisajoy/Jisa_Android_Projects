package com.todo.jisajoy.todo;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {


    private static NoteDatabase noteDatabase;

    public abstract NoteDao noteDao();


    //here in this method 'synchronized' is used for entering single thread at a time
// room call back is used if the room database is already created and load the data.
    //fallbackToDestructiveMigration() method is used because the version increment is not supported in this app, so inorder to avoid the crash which follows.
    public static synchronized NoteDatabase getNoteDatabaseInstance(Context context){
        if (noteDatabase == null){
            noteDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database").fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();
        }
        return noteDatabase;
    }

// this is call already created database
    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDBAsyncTask(noteDatabase).execute();
        }
    };

    private static class populateDBAsyncTask extends AsyncTask<Void, Void, Void>{

        NoteDao noteDao;

        private populateDBAsyncTask(NoteDatabase noteDatabase){
            noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));
            return null;
        }
    }
}
