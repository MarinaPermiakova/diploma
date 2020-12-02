package com.example.android.com.diploma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final LayoutInflater mInflater;
    private List<Note> mNotes; // Cached copy of words
    private static ClickListener clickListener;

    NoteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        if (mNotes != null) {
            Note current = mNotes.get(position);
            holder.titleItemView.setText(current.getTitle());
            holder.infoItemView.setText(current.getInfo());

            if (current.getIsDeadline()){
              DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            holder.deadlineView.setText(df.format(current.getDeadline()));
            } else {
                holder.deadlineView.setVisibility(View.GONE);
            }
            //holder.bindTo(current);


        } else {
            // Covers the case of data not being ready yet.
            holder.titleItemView.setText("No Note");
            //holder.infoItemView.setText("No info");
        }
    }

    void setmNotes(List<Note> notes) {
        mNotes = notes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mNotes != null)
            return mNotes.size();
        else return 0;
    }

    public Note getNoteAtPosition(int position) {
        return mNotes.get(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleItemView;
        private final TextView infoItemView;
        private final TextView deadlineView;

        private NoteViewHolder(View itemView) {
            super(itemView);
            titleItemView = itemView.findViewById(R.id.title);
            infoItemView = itemView.findViewById(R.id.newsTitle);
            deadlineView = itemView.findViewById(R.id.date_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    //void bindTo(Note currentNote){
    //Populate the textviews with data
    //  titleItemView.setText(currentNote.getTitle());
    //  infoItemView.setText(currentNote.getInfo());

    //  if (currentNote.getIsDeadline()){
    //  DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    // deadlineView.setText(df.format(currentNote.getDeadline()));
    // } else {
    //  deadlineView.setVisibility(View.GONE);
    // }
    // }


    // @Override
    // public void onClick(View view) {

    //   Note currentNote = mNotes.get(getAdapterPosition());
    //  Intent detailIntent = new Intent(mContext, DetailActivity.class);
    // detailIntent.putExtra("title", currentNote.getTitle());
    //  detailIntent.putExtra("info", currentNote.getInfo());
    //  detailIntent.putExtra("deadline", currentNote.getDeadline().getTime());
    //  detailIntent.putExtra("hasdeadline", currentNote.getIsDeadline());

    //  ((Activity) mContext).startActivityForResult(detailIntent, NEW_WORD_ACTIVITY_REQUEST_CODE);

    //.startActivity(detailIntent);
    // }
    // }


    public void setOnItemClickListener(ClickListener clickListener) {
        NoteListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }

}

