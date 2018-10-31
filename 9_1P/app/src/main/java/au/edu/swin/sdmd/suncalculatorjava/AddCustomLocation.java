package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    }

    View.OnClickListener confirmListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!confirm_data().equals("")) {
                outputData(confirm_data());
            }
            else
            {
             //todo warn user first
            }
        }
    };

    public String confirm_data()
    {
        EditText tName = findViewById(R.id.name_text);
        EditText tLatitude = findViewById(R.id.latitude_text);
        EditText tLongitude = findViewById(R.id.longitude_text);
        EditText tTimezone = findViewById(R.id.timezone_text);

        String name = tName.getText().toString();
        String latitude = tLatitude.getText().toString();
        String longitude = tLongitude.getText().toString();
        String timezone = tTimezone.getText().toString();
        String outString ="";

        if(nullOrBlank(name)|| nullOrBlank(latitude)||nullOrBlank(longitude)||nullOrBlank(timezone))
        {
            Log.i("Custom_location_null", "null found on either of: "+ name + latitude + longitude + timezone);
        }
        else {
            outString = name + "," + latitude + "," + longitude + "," + timezone;
            return outString;
        }
        return outString;
    }

    public void outputData(String data)
    {

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("custom_locations.txt", Context.MODE_PRIVATE);
        } catch (IOException e) {
            Log.i("IOException", "can't find custom location file");
        }
        try {
            fos.write(data.getBytes());
        } catch (IOException e) {
            Log.i("Custom_location_output", "could not output: " + data);
        }

    }

    //check for null strings
    public static boolean nullOrBlank(String s)
    {
        return (s == null || s.trim().equals(""));
    }

}
