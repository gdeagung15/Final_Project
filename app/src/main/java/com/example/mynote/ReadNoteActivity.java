package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynote.notedatabase.NoteEntity;
import com.example.mynote.notedatabase.NoteViewModel;

public class ReadNoteActivity extends AppCompatActivity {

    TextView noteTitle;
    TextView noteContents;

    Button editButton;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);
        noteTitle = findViewById(R.id.note_title);
        noteContents = findViewById(R.id.note_contents);
        editButton = findViewById(R.id.button_edit_note);
        deleteButton = findViewById(R.id.button_delete_note);

        Bundle bundle = getIntent().getExtras();
        NoteEntity noteEntity = bundle.getBundle("BUNDLE").getParcelable("OBJECT");

        noteTitle.setText(bundle.getString("TITLE"));
        noteContents.setText(bundle.getString("DESC"));

        NoteViewModel noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteTitleValue = noteTitle.getText().toString();
                String noteContentsValue = noteContents.getText().toString();

                boolean isFieldsEmpty = TextUtils.isEmpty(noteTitleValue.trim()) || TextUtils.isEmpty(noteContentsValue.trim());

                if (!isFieldsEmpty) {
                    noteEntity.setContent(noteContentsValue);
                    noteEntity.setTitle(noteTitleValue);

                    noteViewModel.updateNote(noteEntity);
                    finish();
                } else {
                    Toast.makeText(view.getContext(), "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteViewModel.deleteNote(noteEntity);
                finish();
            }
        });


    }
}