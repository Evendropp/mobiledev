package com.example.tyler.a3_2p;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI();

    }
    /*User interface initialisation*/
    public void initialiseUI()
    {
        /*Button Listener init*/
        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(convertButtList);
    }

    /*convert inches listener*/
    View.OnClickListener convertButtList = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            convertInch();
        }
    };

    public void convertInch()
    {
        EditText inputInch = findViewById(R.id.inchesText);
        String dInch = convertImperialCm(inputInch.getText().toString(),"inch");
        TextView convertInch = findViewById(R.id.centimeters);
        convertInch.setText(dInch);
    }
    /*convert imperial to centimeters given imperial type*/
    public String convertImperialCm(String dText, String type)
    {
            switch (type) {
                case "inch": {
                    double cm = Double.parseDouble(dText);
                    double inch = cm * (2.54);
                    return String.format("%3.2f", inch);
                }
                case "feet": {
                    double cm = Double.parseDouble(dText);
                    double feet = cm * (2.54) * (12);
                    return String.format("%3.2f", feet);
                }
                case "miles": {
                    double cm = Double.parseDouble(dText);
                    double miles = cm * (2.54) * (12) * (5280);
                    return String.format("%3.2f", miles);
                }
                default: return "error with input";
            }

        }

}
