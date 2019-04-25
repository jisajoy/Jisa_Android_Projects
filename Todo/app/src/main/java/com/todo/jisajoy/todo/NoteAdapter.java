package com.todo.jisajoy.todo;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

//    private List<Note> notes = new ArrayList<>();
    private onItemClickListener onItemClickListener;

    protected NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_card, viewGroup, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        Note note = getItem(i);
        noteHolder.textViewTitle.setText(note.getTitle());
        noteHolder.textViewPriority.setText(String.valueOf(note.getPriority()));
        noteHolder.textViewDescription.setText(note.getDescription());
    }

//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }

//    public void setNotes(List<Note> notes){
//        this.notes = notes;
//        notifyDataSetChanged();
//    }

    public Note getNotes(int position){
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.title);
            textViewDescription = itemView.findViewById(R.id.description);
            textViewPriority = itemView.findViewById(R.id.priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(Note note);
    }

    public void onItemClickCallerMethod(onItemClickListener onItemClickListener){
this.onItemClickListener = onItemClickListener;
    }
}
