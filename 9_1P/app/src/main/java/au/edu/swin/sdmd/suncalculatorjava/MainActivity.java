package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import au.edu.swin.sdmd.suncalculatorjava.calc.AstronomicalCalendar;
import au.edu.swin.sdmd.suncalculatorjava.calc.GeoLocation;

public class MainActivity extends AppCompatActivity {

    public ArrayList<GeoLocation> localList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeUI();
    }

    private void initializeUI() {
        DatePicker dp = findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dp.init(year,month,day,dateChangeHandler); // setup initial values and reg. handler
        updateTime(year, month, day);

        TextView location = findViewById(R.id.locationTV);
        location.setOnClickListener(locationClick);

        BufferedReader b = null;
        try {
            //get file data
            //b = new BufferedReader(new InputStreamReader(getAssets().open("au_locations.txt")));
            b = new BufferedReader(new InputStreamReader(getAssets().open("custom_locations.txt")));
        }
        catch (IOException e)
        {
            Log.i("io Exception", "can't find location data file");
        }
        String currentLine;
        ArrayList<String> fileData = new ArrayList<String>();
        try{
            while ((currentLine = b.readLine())!= null)
            {
                fileData.add(currentLine);
                Log.i("Current_locationName",currentLine);
            }

        }
        catch(IOException e)
        {
            Log.i("io Exception", "got null in file");
        }
        for(String s : fileData)
        {
            String[] data = s.split(",");
            String cityName = data[0];
            double latitude = Double.parseDouble(data[1]);
            double longitude = Double.parseDouble(data[2]);
            String timezone = data[3];
            android.location.Location locData = new android.location.Location("");
            locData.setLatitude(latitude);
            locData.setLongitude(longitude);
            TimeZone tz = TimeZone.getTimeZone(timezone);
            GeoLocation geoFromFile = new GeoLocation(cityName,locData.getLatitude(),locData.getLongitude(),tz);
            localList.add(geoFromFile);
        }

        updateTime(year, month, day);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if (data != null)
            {
                TextView locationName = findViewById(R.id.locationTV);
                locationName.setText(data.getStringExtra("name"));
                /*Log.i("name_set",locationName.getText().toString());
                Log.i("name_recieved",data.getStringExtra("name"));*/
                DatePicker d = findViewById(R.id.datePicker);
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                d.init(year,month,day,dateChangeHandler);
                updateTime(year,month,day);
            }
        }
    }

    View.OnClickListener locationClick = new  View.OnClickListener()
    {
        @Override
      public void onClick (View v)
      {
          Intent intent = new Intent(getApplicationContext(),LocationList.class );
          startActivityForResult(intent, 0);
      }
    };

    private void updateTime(int year, int monthOfYear, int dayOfMonth) {
        /*TimeZone tz = TimeZone.getDefault();
        GeoLocation geolocation = new GeoLocation("Melbourne", -37.50, 145.01, tz);*/
        TextView locationView = findViewById(R.id.locationTV);
        String locationText = locationView.getText().toString();
        GeoLocation geolocation = new GeoLocation();
        for(int i = 0; i < localList.size();i++)
        {
            String fromFile = localList.get(i).getLocationName();
            if(locationText.trim().equals(fromFile.trim()))
            {
                String name = localList.get(i).getLocationName();
                double lat = localList.get(i).getLatitude();
                double longi = localList.get(i).getLongitude();
                TimeZone timez = localList.get(i).getTimeZone();
                geolocation = new GeoLocation(name, lat, longi, timez);
                Log.i("set_name",name);
            }
        }



        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        Date srise = ac.getSunrise();
        Date sset = ac.getSunset();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        TextView sunriseTV = findViewById(R.id.sunriseTimeTV);
        TextView sunsetTV = findViewById(R.id.sunsetTimeTV);
        Log.d("SUNRISE Unformatted", srise+"");

        sunriseTV.setText(sdf.format(srise));
        sunsetTV.setText(sdf.format(sset));
    }

    DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener()
    {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth)
        {
            updateTime(year, monthOfYear, dayOfMonth);
        }
    };


}
