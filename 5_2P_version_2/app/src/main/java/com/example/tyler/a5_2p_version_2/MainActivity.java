package com.example.tyler.a5_2p_version_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }


    public void initUI() {
        TableRow Row_1 = findViewById(R.id.ROW1);
        Row_1.setOnClickListener(row_1);
    }


    View.OnClickListener row_1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setFocusImage("image 1");
        }
    };

    public void setFocusImage(String image_name)
    {
        //set intent for the focus
        Intent intent = new Intent(getApplicationContext(),FocusImage.class);
        //add the title of what we are focusing on
        intent.putExtra("name", image_name);
         startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data != null)
        {
            ArrayList<image_data> images = data.getParcelableArrayListExtra("IMAGE_DATA");
            image_data i = images.get(0);
            Integer nText = Integer.valueOf(data.getStringExtra("ViewID"));
            if (nText == R.id.name_1)
            {
                TextView textChange = findViewById(R.id.name_1);
                textChange.setText(i.toString());
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
