package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mynote.notedatabase.NoteAdapter;
import com.example.mynote.notedatabase.NoteEntity;
import com.example.mynote.notedatabase.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addNoteButton;
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_notes);
        final NoteAdapter adapter = new NoteAdapter(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(List<NoteEntity> noteEntities) {
                adapter.setNotes(noteEntities);
            }
        });

        addNoteButton = findViewById(R.id.fab_add_note);

        addNoteButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
        });
    }
}