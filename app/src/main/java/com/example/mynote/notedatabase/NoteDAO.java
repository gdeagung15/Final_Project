package com.example.mynote.notedatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    void addNote(NoteEntity noteEntity);

    @Query("SELECT * FROM notes")
    LiveData<List<NoteEntity>> getAllNotes();

    @Update
    void updateNote(NoteEntity noteEntity);

    @Delete
    void deleteNote(NoteEntity noteEntity);

}
