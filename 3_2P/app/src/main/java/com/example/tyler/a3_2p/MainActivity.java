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
            convertFeet();
            convertMiles();
        }
    };

    /*convert inches only*/
    public void convertInch()
    {
        EditText inputInch = findViewById(R.id.inchesText);
        String dInch = convertImperialCm(inputInch.getText().toString(),"inch");
        TextView convertInch = findViewById(R.id.centimeters);
        convertInch.setText(dInch);
    }

    public void convertFeet()
    {
        EditText inputFeet = findViewById(R.id.feetText);
        String dFeet = convertImperialCm(inputFeet.getText().toString(), "feet");
        TextView convertFeet = findViewById(R.id.feet);
        convertFeet.setText(dFeet);
    }

    public void convertMiles()
    {
        EditText inputMiles = findViewById(R.id.milesText);
        String dMiles = convertImperialCm(inputMiles.getText().toString(), "miles");
        TextView convertMiles = findViewById(R.id.miles);
        convertMiles.setText(dMiles);
    }
    /*convert imperial to centimeters given imperial type*/
    public String convertImperialCm(String dText, String type)
    {
        try {
            switch (type) {

                    case "inch": {
                        double cm = Double.parseDouble(dText);
                        double inch = cm * (2.54);
                        return String.format("%3.2f", inch);
                    }
                    case "feet": {
                        double cm = Double.parseDouble(dText);
                        double feet = cm * (2.54) * (12);
                        return String.format("%3.1f", feet);
                    }
                    case "miles": {
                        double cm = Double.parseDouble(dText);
                        double miles = cm * (2.54) * (12) * (5280);
                        return String.format("%1f", miles);
                    }
                    default:
                        return "error with input";
                }

            }

            catch (NumberFormatException nfe)
            {
                return "ERR";
            }
        }
}
