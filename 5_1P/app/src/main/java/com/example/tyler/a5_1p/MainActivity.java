package com.example.tyler.a5_1p;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView bookRecyclerView;
    private RecyclerView.Adapter bookAdapter;
    private RecyclerView.LayoutManager bookLayoutManager;
    private Books mbooks;
    List<String> mBooknames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiliseUI(savedInstanceState);
    }

    public void initiliseUI(Bundle savedInstanceState)
    {
        //set list up
        mbooks = Books.get(getApplicationContext());
        mBooknames = mbooks.getBookNames();
        //adapter for recycle view
        bookRecyclerView = findViewById(R.id.recycler_view);
        //use layout manager
        bookLayoutManager = new LinearLayoutManager(this);
        bookRecyclerView.setLayoutManager(bookLayoutManager);
        //set adapter
        bookAdapter = new bAdapter(mBooknames);
        bookRecyclerView.setAdapter(bookAdapter);

    }

    public class bAdapter extends RecyclerView.Adapter<bAdapter.ViewHolder>
    {
        List<String> mData;


        public  bAdapter(List<String> data) {
            mData = data;
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView mTitleText;
            public TextView mRatingsText;
            //needs image added
            public ViewHolder(View v)
            {
                super(v);
                mTitleText = v.findViewById(R.id.item_title);
                mRatingsText = v.findViewById(R.id.item_rating);
            }

        }


        @NonNull
        @Override
        public bAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
            v.setOnClickListener(clickList);
            ViewHolder vh = new ViewHolder(v);
            return vh;

        }

        @Override
        public void onBindViewHolder(@NonNull bAdapter.ViewHolder viewHolder, int i) {
        String name = mData.get(i);
        viewHolder.mTitleText.setText(name);
        Book bk = mbooks.getBook(name);
        viewHolder.mRatingsText.setText(bk.getRating());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
    View.OnClickListener clickList = new  View.OnClickListener()
    {
        @Override
        public void onClick(View v){

        }
    };

}
