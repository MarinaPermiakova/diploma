package com.example.android.com.diploma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final LayoutInflater mInflater;
    private List<Note> mNotes;
    private static ClickListener clickListener;

    NoteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);

            if (current.getTitle().isEmpty()) {
                holder.titleItemView.setVisibility(View.GONE);
            } else
                holder.titleItemView.setText(current.getTitle());

            if (current.getInfo().isEmpty()) {
                holder.infoItemView.setVisibility(View.GONE);
            } else
                holder.infoItemView.setText(current.getInfo());

            if (current.getIsDeadline()) {
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
                holder.deadlineView.setText(df.format(current.getDeadline()));
            } else {
                holder.deadlineView.setVisibility(View.GONE);
            }

        } else {
            holder.titleItemView.setText(R.string.no_note);
        }
    }

    void setNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public Note getNoteAtPosition(int position) {
        return mNotes.get(position);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleItemView;
        private final TextView infoItemView;
        private final TextView deadlineView;

        private NoteViewHolder(View itemView) {
            super(itemView);
            titleItemView = itemView.findViewById(R.id.title);
            infoItemView = itemView.findViewById(R.id.newsTitle);
            deadlineView = itemView.findViewById(R.id.date_time);
            itemView.setOnClickListener(view -> clickListener.onItemClick(view, getAdapterPosition()));
        }
    }


    public void setOnItemClickListener(ClickListener clickListener) {
        NoteListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}



