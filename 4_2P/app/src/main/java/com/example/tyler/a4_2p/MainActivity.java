package com.example.tyler.a4_2p;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }

    public void initialiseUI()
    {
        ImageView mango1 = findViewById(R.id.mango1);
        mango1.setOnClickListener(mango1list);
    }

    View.OnClickListener mango1list = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
         startMango1();
        }
    };
    public void startMango1()
    {
        String activeMango;
        Intent openMango1 = new Intent(this , ImageDisplayActivity.class);
        startActivity(openMango1);
    }


}
