package com.example.mynote;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mynote.notedatabase.NoteDAO;
import com.example.mynote.notedatabase.NoteEntity;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class MyNoteDatabase extends RoomDatabase {
    public abstract NoteDAO noteDAO();
    private static MyNoteDatabase INSTANCE;

    public static MyNoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyNoteDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyNoteDatabase.class, "mynotedb").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
