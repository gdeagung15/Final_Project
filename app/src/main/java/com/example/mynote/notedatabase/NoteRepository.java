package com.example.mynote.notedatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mynote.MyNoteDatabase;

import java.util.List;

public class NoteRepository {
    private NoteDAO noteDAO;
    private LiveData<List<NoteEntity>> allNotes;

    public NoteRepository(Application application) {
        MyNoteDatabase noteDB = MyNoteDatabase.getDatabase(application);
        noteDAO = noteDB.noteDAO();
        allNotes = noteDAO.getAllNotes();
    }

    LiveData<List<NoteEntity>> getAllNotes() {
        return allNotes;
    }

    public void insert(NoteEntity noteEntity) {
        new NoteAsyncTask(noteDAO, CRUDTask.INSERT).execute(noteEntity);
    }

    public void update(NoteEntity noteEntity) {
        new NoteAsyncTask(noteDAO, CRUDTask.UPDATE).execute(noteEntity);
    }

    public void delete(NoteEntity noteEntity) {
        new NoteAsyncTask(noteDAO, CRUDTask.DELETE).execute(noteEntity);
    }

    private static class NoteAsyncTask extends AsyncTask<NoteEntity, Void, Void> {
        private NoteDAO asyncTaskDao;
        private CRUDTask crudTask;


        NoteAsyncTask(NoteDAO noteDAO, CRUDTask crudTask) {
            asyncTaskDao = noteDAO;
            this.crudTask = crudTask;
        }

        @Override
        protected Void doInBackground(NoteEntity... noteEntities) {
            if (this.crudTask == CRUDTask.INSERT) {
                asyncTaskDao.addNote(noteEntities[0]);
            } else if (this.crudTask == CRUDTask.UPDATE) {
                asyncTaskDao.updateNote(noteEntities[0]);
            } else if (this.crudTask == CRUDTask.DELETE) {
                asyncTaskDao.deleteNote(noteEntities[0]);
            }
            return null;
        }
    }


}
