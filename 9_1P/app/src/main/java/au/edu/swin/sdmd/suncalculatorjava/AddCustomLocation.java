package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class AddCustomLocation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_location);
        initUI();
    }

    public void initUI()
    {
        Button confirm = findViewById(R.id.confirm_button);
        confirm.setOnClickListener(confirmListener);
        ArrayList<String> timezones = loadTimeZones();
        Spinner timezoneSpinner = findViewById(R.id.timezone_text);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timezones);
        timezoneSpinner.setAdapter(adapter);
    }

    View.OnClickListener confirmListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!confirm_data().equals("")) {
                outputData(confirm_data());
                Log.i("data_output", "data: " + confirm_data());
            }
            else
            {
                String failed = "enter Data first";
                Toast enterData = Toast.makeText(getApplicationContext(),failed,Toast.LENGTH_SHORT);
                enterData.show();
            }
        }
    };

    public String confirm_data()
    {
        EditText tName = findViewById(R.id.name_text);
        EditText tLatitude = findViewById(R.id.latitude_text);
        EditText tLongitude = findViewById(R.id.longitude_text);
        Spinner tTimezone = findViewById(R.id.timezone_text);
        String[] splitTimezone = tTimezone.getSelectedItem().toString().split(" ");

        String name = tName.getText().toString();
        double latitude = Double.parseDouble(tLatitude.getText().toString());
        double longitude = Double.parseDouble(tLongitude.getText().toString());

        String timezone = splitTimezone[1];

        String outString ="";

        //Check each part to add to check for nulls or missing parts
        if((nullOrBlank(name)||nullOrBlank(timezone))|| Double.isNaN(latitude)||Double.isNaN(longitude))
        {
            Log.i("Custom_location_null", "null found on either of: "+ name + latitude + longitude + timezone);
        }
        else {
            outString = name + "," + latitude + "," + longitude + "," + timezone+ "\n\r";
            return outString;
        }
        return outString;
    }

    public void outputData(String data)
    {
        try {
            FileOutputStream fileOut = openFileOutput("custom_locations.txt",MODE_APPEND);
            OutputStreamWriter outStream = new OutputStreamWriter(fileOut);
            outStream.append(data);
            outStream.close();
            Log.i("Write_to_file",data);
        }
        catch (IOException e)
        {
            Log.i("Write_to_file",e.toString());
        }
        //Intent intent = new Intent(getApplicationContext(),LocationList.class);
        finish();
    }

    //check for null strings
    public static boolean nullOrBlank(String s)
    {
        return (s == null || s.trim().equals(""));
    }

    private ArrayList<String> loadTimeZones() {
        String[] allIDs = TimeZone.getAvailableIDs();
        ArrayList<String> ausIDs = new ArrayList<>();
        long hours = 0;
        long minutes = 0;
        //String formatted = "";
        for (String id : allIDs) {
            if (id.contains("Australia")) {
                TimeZone tz = TimeZone.getTimeZone(id);
                hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
                minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                        - TimeUnit.HOURS.toMinutes(hours);
                // avoid -4:-30 issue
                minutes = Math.abs(minutes);
                String formatted = null;
                if (hours > 0) {
                    formatted = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
                } else {
                    formatted = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
                }
                Log.i("ADDLOCATION", "formatted string: " + formatted);
                ausIDs.add(formatted);
            }
        }
        return ausIDs;
    }

}
