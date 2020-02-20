package com.example.contactosdb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import static androidx.core.content.ContextCompat.startActivity;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private LinkedList<String> mWordList;
    private LayoutInflater mInflater;
    private Context mcontext;
    public WordListAdapter(Context context, LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
        this.mcontext= context;

    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){ // Inflate an item view.
        View mItemView = mInflater.inflate(R.layout.row, parent, false);
        return new WordViewHolder(mItemView, this,mcontext);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }



    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView wordItemView;
        private WordListAdapter mAdapter;
        private Context context;
        public WordViewHolder(View itemView, WordListAdapter adapter ,Context context) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.word);
            this.context= context;
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //wordItemView.setText ("Clicked! "+ wordItemView.getText());
            //MainActivity.detalles();
        }

        public void detalles(){
            //startActivity(new Intent(context, pantallaDetalles.class));
            Intent intent = new Intent(context, pantallaDetalles.class);
            //intent.putExtra(EXTRA_ID, id);
            //intent.putExtra(EXTRA_POSITION, h.getAdapterPosition());
            //intent.putExtra(EXTRA_WORD, word);
            context.startActivity(intent);
            //((Activity) context).startActivityForResult(intent, WordListAdapter);
        }

    }
}
