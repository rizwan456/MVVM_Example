package com.example.mvvm_example;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_example.databinding.NoteItemViewBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes=new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item,viewGroup,false);
        return new NoteHolder(NoteItemViewBinding.bind(itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        Note currentNote=notes.get(i);
        noteHolder.getitemView().textViewTitle.setText(currentNote.getTitle());
        noteHolder.getitemView().textViewDescription.setText(currentNote.getDescription());
        noteHolder.getitemView().textViewPriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        NoteItemViewBinding noteItemViewBinding;
        public  NoteHolder(NoteItemViewBinding noteItemViewBinding){
            super(noteItemViewBinding.getRoot());
            this.noteItemViewBinding=noteItemViewBinding;
        }

        public void bindData(){

        }

        public NoteItemViewBinding getitemView(){
            return noteItemViewBinding;
        }
    }
}
