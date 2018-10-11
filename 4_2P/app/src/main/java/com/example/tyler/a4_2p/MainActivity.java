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

    public void initialiseUI() {
        //create click listeners for each image
        ImageView mango1 = findViewById(R.id.mango1);
        mango1.setOnClickListener(mango1list);
        ImageView mango2 = findViewById(R.id.mango2);
        mango2.setOnClickListener(mango2list);
        ImageView mango3 = findViewById(R.id.mango3);
        mango3.setOnClickListener(mango3list);
        ImageView mango4 = findViewById(R.id.mango4);
        mango4.setOnClickListener(mango4list);
    }

    //click listeners for each image that send the information of what is clicked to the
    // setActiveMango function
    View.OnClickListener mango1list = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startMango1();
        }
    };

    public void startMango1() {
        int draw = R.drawable.mango1;
        String name = getResources().getString(R.string.mango1);
        setActiveMango(draw, name);
    }

    View.OnClickListener mango2list = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int draw = R.drawable.mango2;
            String name = getResources().getString(R.string.mango2);
            setActiveMango(draw, name);
        }
    };

    View.OnClickListener mango3list = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int draw = R.drawable.mango3;
            String name = getResources().getString(R.string.mango3);
            setActiveMango(draw, name);
        }
    };

    View.OnClickListener mango4list = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int draw = R.drawable.mango4;
            String name = getResources().getString(R.string.mango4);
            setActiveMango(draw, name);
        }
    };

    //sets using data parsed in from the image clicks, sets the active image as the intent
    //and starts the display activity
    public void setActiveMango (int mangoInt , String mangoStr)
    {
        Intent openMango = new Intent(this , ImageDisplayActivity.class);
        openMango.putExtra("drawMango", mangoInt);
        openMango.putExtra("nameMango", mangoStr);
        startActivity(openMango);
    }

}
