package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynote.notedatabase.NoteEntity;
import com.example.mynote.notedatabase.NoteViewModel;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_KEY = "hello there";

    private EditText noteTitleInput;
    private EditText noteDescInput;
    private Button myButton;

    private String noteTitleValue;
    private String noteDescValue;

    private boolean fieldsEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteTitleInput = findViewById(R.id.input_judul_catatan);
        noteDescInput = findViewById(R.id.input_desc_catatan);

        myButton = findViewById(R.id.button_add_note);

        NoteViewModel noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);


        myButton.setOnClickListener(view -> {
            noteTitleValue = noteTitleInput.getText().toString();
            noteDescValue = noteDescInput.getText().toString();

            fieldsEmpty = TextUtils.isEmpty(noteTitleValue.trim()) && TextUtils.isEmpty(noteDescValue.trim());

            if (!fieldsEmpty) {
                NoteEntity noteEntity = new NoteEntity(noteTitleValue, noteDescValue);
                noteViewModel.addNote(noteEntity);

                finish();
            } else {
                Toast.makeText(this.getApplicationContext(), "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}