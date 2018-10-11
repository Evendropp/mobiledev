package com.example.tyler.a4_2p;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        initializeUI(savedInstanceState);
    }

    public void initializeUI(Bundle savedInstanceState)
    {
        //get data from intent
        Bundle mangoData = getIntent().getExtras();
        Drawable mangoDrawn = getResources().getDrawable(mangoData.getInt("drawMango"));
        String mangoDescr = mangoData.getString("nameMango");

        //get objects on screen
        ImageView activeImage = findViewById(R.id.activeImage);
        TextView activeText = findViewById(R.id.activeText);

        //set objects on screen
        activeImage.setImageDrawable(mangoDrawn);
        activeText.setText(mangoDescr);
    }

}
