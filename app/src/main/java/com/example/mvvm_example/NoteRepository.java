package com.example.mvvm_example;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Update;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public  NoteRepository(Application application){
        NoteDataBase dataBase=NoteDataBase.getInstance(application);
        noteDao=dataBase.noteDao();
        allNotes=noteDao.getAllNotes();
    }

    public void insert(Note note){
        new InserNoteAysncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAysncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAysncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNoteAysncTask(noteDao);
    }

    public LiveData<List<Note>> getAllNotes(){
        return  allNotes;
    }

    private  static  class InserNoteAysncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private  InserNoteAysncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private  static  class UpdateNoteAysncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private UpdateNoteAysncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private  static  class DeleteNoteAysncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private  DeleteNoteAysncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private  static  class DeleteAllNoteAysncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private  DeleteAllNoteAysncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
