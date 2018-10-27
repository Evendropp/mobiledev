package com.example.tyler.a5_2p_version_2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Arrays;

public class FocusImage extends Activity {

    image_data image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_image);

        initUI(savedInstanceState);
    }

    //initialise ui
    public void initUI(Bundle b)
    {
        Bundle bundle = getIntent().getExtras();
        ArrayList<image_data> data = getIntent().getParcelableArrayListExtra("IMAGE_DATA");
        image_data d = data.get(0);

        //setting up texts and data
        EditText name = findViewById(R.id.name);
        EditText location = findViewById(R.id.location);
        EditText keywords = findViewById(R.id.Keywords);
        EditText date = findViewById(R.id.date);
        EditText who = findViewById(R.id.who_found);
        EditText rate = findViewById(R.id.Rating);
        ToggleButton sharing = findViewById(R.id.shareable);

        //if the data isnt null, set up the data on the screen
        if(d != null) {
            if(d.getName()!= null)name.setText(d.getName());
            if(d.getLocation()!=null) location.setText(d.getLocation());
            if(d.getDate() !=null) date.setText(d.getDate());
            if(d.getWho()!= null) who.setText(d.getWho());
            if(d.getIsshared() != null) sharing.setChecked(d.getIsshared());
            try {
                rate.setText(Integer.toString(d.getRating()));
            }catch (NumberFormatException nfe)
            {
                rate.setText("0");
            }
            String words = "";
            for(int i = 0; i<d.getKeywords().size(); i++)
            {
                words += ((d.getKeywords().get(i)) + " ");
            }
            keywords.setText(words);
        }

        //focus listeners for each edit text
        name.setOnFocusChangeListener(focused);
        location.setOnFocusChangeListener(focused);
        keywords.setOnFocusChangeListener(focused);
        date.setOnFocusChangeListener(focused);
        who.setOnFocusChangeListener(focused);
        rate.setOnFocusChangeListener(focused);
        sharing.setOnFocusChangeListener(focused);
    }


    // focus change listener for editTexts
    // basically just forces an update/store of the text details when you tap on something else
    View.OnFocusChangeListener focused = new View.OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View view, boolean b) {
            storeData();
        }
    };




    @Override
    public void onBackPressed()
    {
        //finalise data inputs
        storeData();
        Intent activityResult = new Intent();
        ArrayList<image_data> imageData = new ArrayList<>();
        //add image data to send back
        imageData.add(image);
        activityResult.putParcelableArrayListExtra("IMAGE_DATA",imageData);
        activityResult.putExtra("ViewID",getIntent().getStringExtra("name"));
        Log.i("CURRENT_IMAGE", getIntent().getStringExtra("name"));
        //say were done to the return activity
        setResult(RESULT_OK, activityResult);

        super.onBackPressed();
    }

    //store the image data
    public void storeData()
    {
        EditText nameText = findViewById(R.id.name);
        String name = nameText.getText().toString();

        EditText locationText = findViewById(R.id.location);
        String location = locationText.getText().toString();

        EditText keywordsText = findViewById(R.id.Keywords);
        String[] keys = keywordsText.getText().toString().split(" ");
        ArrayList<String> keywords = new ArrayList<>(Arrays.asList(keys));

        EditText dateText = findViewById(R.id.date);
        String date = dateText.getText().toString();


        EditText whoText = findViewById(R.id.who_found);
        String who = whoText.getText().toString();

        EditText rateText = findViewById(R.id.Rating);
        Integer rate = 0;
        try {
            rate = Integer.parseInt(rateText.getText().toString());
        }catch (NumberFormatException nfe)
        {
            Log.i("NOT_A_NUMBER_RATING","RATINGS NOT NUMBER");
        }

        ToggleButton sharingText = findViewById(R.id.shareable);
        Boolean sharing = sharingText.isChecked();

        //update only if not null, else create new image data
        if (image != null)
        {
           image.update(name,location,date,keywords,sharing,who,rate );
        }
        else
        {
            image = new image_data(name,location,date,keywords,sharing,who,rate);
        }
    }
}
