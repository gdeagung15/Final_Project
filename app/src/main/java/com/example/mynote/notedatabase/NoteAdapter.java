package com.example.mynote.notedatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynote.R;
import com.example.mynote.ReadNoteActivity;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<NoteEntity> notes;

    public NoteAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.note_card_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (!notes.isEmpty()) {
            NoteEntity currentNote = notes.get(position);
            holder.cardTitle.setText(currentNote.getTitle());
            holder.cardDesc.setText(currentNote.getContent());

            holder.cardContainer.setOnClickListener(view -> {
                Intent intent = new Intent(context, ReadNoteActivity.class);

                Bundle bundle = new Bundle();
                bundle.putParcelable("OBJECT", currentNote);

                intent.putExtra("TITLE", currentNote.getTitle());
                intent.putExtra("DESC", currentNote.getContent());
                intent.putExtra("BUNDLE", bundle);

                context.startActivity(intent);

            });
        }
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (notes != null) {
            return notes.size();
        } else {
            return 0;
        }
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView cardTitle;
        private final TextView cardDesc;
        private final CardView cardContainer;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            cardContainer = itemView.findViewById(R.id.card_container);
            cardTitle = itemView.findViewById(R.id.title_card_note);
            cardDesc = itemView.findViewById(R.id.desc_card_note);
        }
    }
}
