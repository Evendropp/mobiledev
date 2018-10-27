package com.example.tyler.a5_2p_version_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private image_data ROW_DATA_1;
    private image_data ROW_DATA_2;
    private image_data ROW_DATA_3;
    private image_data ROW_DATA_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    //initialise ui and set listeners
    public void initUI() {
        TableRow Row_1 = findViewById(R.id.ROW1);
        TableRow Row_2 = findViewById(R.id.ROW2);
        TableRow Row_3 = findViewById(R.id.ROW3);
        TableRow Row_4 = findViewById(R.id.ROW4);
        Row_1.setOnClickListener(row_1);
        Row_2.setOnClickListener(row_2);
        Row_3.setOnClickListener(row_3);
        Row_4.setOnClickListener(row_4);
    }

    // row click listener for row 1
    View.OnClickListener row_1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setFocusImage(String.valueOf(R.id.name_1), ROW_DATA_1);
        }
    };

    View.OnClickListener row_2 = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            setFocusImage(String.valueOf(R.id.name_2),ROW_DATA_2);
        }
    };

    View.OnClickListener row_3 = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            setFocusImage(String.valueOf(R.id.name_3),ROW_DATA_3);
        }
    };

    View.OnClickListener row_4 = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            setFocusImage(String.valueOf(R.id.name_4),ROW_DATA_4);
        }
    };

    //set focus image and then start the focus activity
    public void setFocusImage(String image_name, image_data rowData)
    {
        //set intent for the focus
        Intent intent = new Intent(getApplicationContext(),FocusImage.class);
        //add the title of what we are focusing on
        intent.putExtra("name", image_name);
        ArrayList<image_data> rows = new ArrayList<>();
        rows.add(rowData);
        intent.putParcelableArrayListExtra("IMAGE_DATA",rows);
        startActivityForResult(intent, 0);
    }

    // on result do so and so
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK)
        {
            if (data != null)
            {
                ArrayList<image_data> images = data.getParcelableArrayListExtra("IMAGE_DATA");
                image_data img = images.get(0);
                Integer nText = Integer.valueOf(data.getStringExtra("ViewID"));

                TextView textChange;
                TextView dateChange;
                switch (nText) {
                    case R.id.name_1: {
                        textChange = findViewById(R.id.name_1);
                        dateChange = findViewById(R.id.date_1);
                        textChange.setText(img.getName());
                        dateChange.setText(img.getDate());
                        ROW_DATA_1 = img;
                        break;
                    }
                    case R.id.name_2:{
                        textChange = findViewById(R.id.name_2);
                        dateChange = findViewById(R.id.date_2);
                        textChange.setText(img.getName());
                        dateChange.setText(img.getDate());
                        ROW_DATA_2 = img;
                        break;
                    }
                    case R.id.name_3:{
                        textChange = findViewById(R.id.name_3);
                        dateChange = findViewById(R.id.date_3);
                        textChange.setText(img.getName());
                        dateChange.setText(img.getDate());
                        ROW_DATA_3 = img;
                        break;
                    }
                    case R.id.name_4:{
                        textChange = findViewById(R.id.name_4);
                        dateChange = findViewById(R.id.date_4);
                        textChange.setText(img.getName());
                        dateChange.setText(img.getDate());
                        ROW_DATA_4 = img;
                        break;
                    }
                }
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
