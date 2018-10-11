package com.example.tyler.a5_1p;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

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

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
    View.OnClickListener clickList = new  View.OnClickListener()
    {
        @Override
        public void onClick(View v){

        }
    };

}
