package com.example.tyler.a4_1p;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();
    }

    public void initialiseUI()
    {
        Button selectTemp = findViewById(R.id.tempSelect);
        selectTemp.setOnClickListener(selectedTempList);
        Button selectDist = findViewById(R.id.distanceSelect);
        selectDist.setOnClickListener(selectedDistList);
    }

    View.OnClickListener selectedDistList = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

            distSelected();
        }
    };

    View.OnClickListener selectedTempList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            tempSelected();
        }
    };

    public void distSelected()
    {
        Intent openDist = new Intent(this , DistanceConvert.class);
        startActivity(openDist);
    }

    public void tempSelected()
    {
        Intent openTemp = new Intent(this, TemperatureConv.class);
        startActivity(openTemp);
    }
}
