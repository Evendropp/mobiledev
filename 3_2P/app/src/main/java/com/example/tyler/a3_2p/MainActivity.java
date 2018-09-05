package com.example.tyler.a3_2p;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseUI(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        EditText inchesText = findViewById(R.id.inchesText);
        String saInches = inchesText.getText().toString();
        outState.putString("Inch", saInches);

        EditText feetText = findViewById(R.id.feetText);
        String saFeet = feetText.getText().toString();
        outState.putString("Feet", saFeet);

        EditText milesText = findViewById(R.id.milesText);
        String saMiles = milesText.getText().toString();
        outState.putString("Miles", saMiles);

        super.onSaveInstanceState(outState);
    }

    private void restoreState (Bundle state)
    {
        if (state == null)
        {
            return;
        }
        EditText inchesText = findViewById(R.id.inchesText);
        String saInch = state.getString("Inch");
        inchesText.setText(saInch);

        EditText feetText = findViewById(R.id.feetText);
        String saFeet = state.getString("Feet");
        feetText.setText(saFeet);

        EditText milesText = findViewById(R.id.milesText);
        String saMiles = state.getString("Miles");
        milesText.setText(saMiles);

    }
    /*User interface initialisation*/
    public void initialiseUI(Bundle state)
    {
        /*Button Listener init*/
        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(convertButtList);
        /*bundle restore*/
        restoreState(state);
        convertButton.callOnClick();
    }

    /*convert inches listener*/
    View.OnClickListener convertButtList = new View.OnClickListener() {
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
        String dInch = convertInch(inputInch.getText().toString());
        TextView convertInch = findViewById(R.id.centimeters);
        CheckBox meterCheck = findViewById(R.id.convertType);
        if (meterCheck.isChecked()) {
            convertInch.setText(dInch + " m");
        }
        else {
            convertInch.setText(dInch + " cm");
        }
    }

    public void convertFeet()
    {
        EditText inputFeet = findViewById(R.id.feetText);
        String dFeet = convertFeet(inputFeet.getText().toString());
        TextView convertFeet = findViewById(R.id.feet);
        CheckBox meterCheck = findViewById(R.id.convertType);
        if (meterCheck.isChecked()) {
            convertFeet.setText(dFeet + " m");
        }
        else {
            convertFeet.setText(dFeet + " cm");
        }
    }

    public void convertMiles()
    {
        EditText inputMiles = findViewById(R.id.milesText);
        String dMiles = convertMiles(inputMiles.getText().toString());
        TextView convertMiles = findViewById(R.id.miles);
        CheckBox meterCheck = findViewById(R.id.convertType);
        if (meterCheck.isChecked()) {
            convertMiles.setText(dMiles + " m");
        }
        else {
            convertMiles.setText(dMiles + " cm");
        }
    }
    /*convert imperial to centimeters given imperial type*/
    public String convertInch (String dText)
    {
        try {
            double cm = Double.parseDouble(dText);
            double inch = cm * (2.54);
            CheckBox meterCheck = findViewById(R.id.convertType);
            if (meterCheck.isChecked())
            {
                inch *= 0.01;
            }
            return String.format("%3.2f", inch);
        }

            catch (NumberFormatException nfe)
            {
                return "0";
            }
    }

    public String convertFeet (String dText)
    {
        try{
            double cm = Double.parseDouble(dText);
            double feet = cm * (2.54) * (12);
            CheckBox meterCheck = findViewById(R.id.convertType);
            if (meterCheck.isChecked())
            {
                feet *= 0.01;
            }
            return String.format("%3.1f", feet);
        }
        catch (NumberFormatException nfe)
        {
            return "0";
        }

    }
    public String convertMiles (String dText)
    {
        try {
            double cm = Double.parseDouble(dText);
            double miles = cm * (2.54) * (12) * (5280);
            CheckBox meterCheck = findViewById(R.id.convertType);
            if (meterCheck.isChecked())
            {
                miles *= 0.01;
            }
            return String.format("%1.1f", miles);
        }
        catch (NumberFormatException nfe)
        {
            return "0";
        }

    }
}
