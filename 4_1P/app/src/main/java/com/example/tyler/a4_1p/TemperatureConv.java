package com.example.tyler.a4_1p;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TemperatureConv extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_conv);
        initialiseUI(savedInstanceState);
    }

    //on rotate store current info in bundle
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        EditText tempText = findViewById(R.id.TempInput);
        String saveTemp = tempText.getText().toString();
        outState.putString("temp",saveTemp);

        super.onSaveInstanceState(outState);
    }

    //restore after rotate if possible
    private void restoreState (Bundle state)
    {
        if (state == null)
        {
            return;
        }
        EditText tempText = findViewById(R.id.TempInput);
        String savedTemp = state.getString("temp");
        tempText.setText(savedTemp);

    }

    // on start up create listener for button and restore any previous data if possible
    public void initialiseUI( Bundle state)
    {
        /*button listener for convert*/
        Button convertButton = findViewById(R.id.convertTemp);
        convertButton.setOnClickListener(convertButtonList);
        /*bundle restore data*/
        restoreState(state);
        convertButton.callOnClick();
    }

    View.OnClickListener convertButtonList = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){

            EditText inputTemp = findViewById(R.id.TempInput);
            String dTemp = convertTemp(inputTemp.getText().toString());
            TextView convertTemp = findViewById(R.id.TempOutput);
            convertTemp.setText(dTemp + "F");
        }

    };


    public String convertTemp(String sCelsius ) {
        try {
            double celsius = Double.parseDouble(sCelsius);
            double fahrenheit = celsius * (9 / 5) + 32;
            return String.format("%3.2f", fahrenheit);
        } catch (NumberFormatException nfe) {
            return "0";
        }
    }
}
