package com.android.roomwordssample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    private List<Word> mWords;
    private OnWordClickListener mWordClickListener;

    public WordListAdapter(Context context, OnWordClickListener wordClickListener) {
        mInflater = LayoutInflater.from(context);
        this.mWordClickListener = wordClickListener;
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new WordViewHolder(itemView, mWordClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder wordViewHolder, int i) {

        if (mWords != null) {
            Word current = mWords.get(i);
            wordViewHolder.wordItemView.setText(current.getWord());
        } else {
            wordViewHolder.wordItemView.setText("No Word");
        }

    }

    public List<Word> getWords() {
        return mWords;
    }

    void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }

    public Word getWordAtPosition(int position) {
        return mWords.get(position);
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView wordItemView;
        OnWordClickListener wordClickListener;

        public WordViewHolder(@NonNull View itemView, OnWordClickListener wordClickListener) {
            super(itemView);

            wordItemView = itemView.findViewById(R.id.textView);
            this.wordClickListener = wordClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            wordClickListener.onWordClick(getAdapterPosition());
        }
    }
}
