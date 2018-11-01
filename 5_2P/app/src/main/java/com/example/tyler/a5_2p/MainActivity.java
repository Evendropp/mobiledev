package com.example.tyler.a5_2p;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Fruits mango1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiliseUI(savedInstanceState);
    }

    public void initiliseUI(Bundle bundle)
    {
        Bundle data = getIntent().getExtras();
        //setUpFruit();

        ImageView mangoPic1 = findViewById(R.id.item_image_1);
        mangoPic1.setOnClickListener(mango1List);
    }

    View.OnClickListener mango1List = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            focusMango(String.valueOf(R.id.item_image_1));
        }
    };

    public void focusMango(String mangoName)
    {
        Intent intent = new Intent(this, FormActivity.class);
        intent.putExtra("Drawable", R.drawable.mango1);
        intent.putExtra("mango", mangoName);

        startActivityForResult(intent,0);
        /*switch (i) {
            case 1:{
                intent.putExtra("mangoFocus", mango1);
                mangoDrawable = R.drawable.mango1;
                intent.putExtra("Drawable", mangoDrawable);
                startActivity(intent); }*/

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("null?", "test for null");
        if (data != null)
        {
            Log.i("null?", "nonnull");
            ArrayList<Fruits>fruits = data.getParcelableArrayListExtra("mangoData");
            Fruits f = fruits.get(0);
            int text = Integer.valueOf(data.getStringExtra("mangoView"));
            switch (text) {
                case R.id.item_name_1:
                    TextView tChange1 = findViewById(R.id.item_name_1);
                    tChange1.setText(f.toString());
                    break;
            }
        }
    }
}
