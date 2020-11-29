package com.example.mynote.notedatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<NoteEntity>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public LiveData<List<NoteEntity>> getAllNotes() {
        return allNotes;
    }

    public void addNote(NoteEntity noteEntity) {
        noteRepository.insert(noteEntity);
    }

    public void updateNote(NoteEntity noteEntity) {
        noteRepository.update(noteEntity);
    }

    public void deleteNote(NoteEntity noteEntity) {
        noteRepository.delete(noteEntity);
    }

}
