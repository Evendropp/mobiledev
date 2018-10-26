package com.example.tyler.a5_2p_version_2;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

public class FocusImage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_image);

        initUI(savedInstanceState);
    }

    public void initUI(Bundle b)
    {
        Bundle bundle = getIntent().getExtras();
        EditText name = findViewById(R.id.name);
        EditText location = findViewById(R.id.location);
        EditText keywords = findViewById(R.id.Keywords);
        EditText date = findViewById(R.id.date);
        EditText who = findViewById(R.id.who_found);
        EditText rate = findViewById(R.id.Rating);
        ToggleButton sharing = findViewById(R.id.shareable);

        name.setOnFocusChangeListener(focused);
        location.setOnFocusChangeListener(focused);
        keywords.setOnFocusChangeListener(focused);
        date.setOnFocusChangeListener(focused);
        who.setOnFocusChangeListener(focused);
        rate.setOnFocusChangeListener(focused);
        sharing.setOnFocusChangeListener(focused);
    }

    View.OnFocusChangeListener focused = new View.OnFocusChangeListener()
    {
        @Override
        public void onFocusChange(View view, boolean b) {
            storeData();
        }
    };


    public void storeData()
    {
        
    }
}
