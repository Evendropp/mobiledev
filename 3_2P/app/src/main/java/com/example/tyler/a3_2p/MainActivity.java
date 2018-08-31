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
        Button convertInches = findViewById(R.id.buttonInches);
        convertInches.setOnClickListener(convertInchesListen);
    }

    /*convert inches listener*/
    View.OnClickListener convertInchesListen = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            EditText inputInch = findViewById(R.id.inchesText);
            String dInch = changeInches(inputInch.getText().toString());
            TextView convertInch = findViewById(R.id.centimeters);
            convertInch.setText(dInch);
        }
    };


    /*convert inches*/
    public String changeInches(String dCm)
    {
        try {
            double cm = Double.parseDouble(dCm);
            double inch = cm*(2.54);
            return String.format("%3.2f", inch);
        }
        catch (NumberFormatException nfe)
        {
            return "error with input";
        }
    }
}
