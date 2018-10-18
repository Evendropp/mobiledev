package com.example.tyler.a5_2p;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {

    private Fruits activeMango;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_image);
        initiliseUI();
    }

    public void initiliseUI()
    {
        Bundle bundle = getIntent().getExtras();
        activeMango = bundle.getParcelable("mangoFocus");
        Drawable drawable = getResources().getDrawable(bundle.getInt("Drawable"));

        ImageView setImage = findViewById(R.id.focus_image);
        TextView setTextName = findViewById(R.id.item_name);

        setImage.setImageDrawable(drawable);
        setTextName.setText(activeMango.getName());

        //button listener
        Button saver = findViewById(R.id.save_return_button);
        saver.setOnClickListener(saveButt);
    }


    View.OnClickListener saveButt = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // save data
            EditText textName = findViewById(R.id.item_name_edit);
            activeMango.setName(textName.getText().toString());
            returnToMain();
        }
    };

    public void returnToMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("mango", activeMango);
        startActivity(intent);
    }

}
