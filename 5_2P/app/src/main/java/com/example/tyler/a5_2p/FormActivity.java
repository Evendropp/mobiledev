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

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {

    private Fruits activeMango;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_image);
        initiliseUI(savedInstanceState);
    }

    public void initiliseUI(Bundle bundle)
    {
        Bundle data = getIntent().getExtras();
        //activeMango = bundle.getParcelable("mangoFocus");
        //Drawable drawable = getResources().getDrawable(bundle.getInt("Drawable"));

        ImageView setImage = findViewById(R.id.focus_image);
        TextView setTextName = findViewById(R.id.item_name_edit);

        setImage.setOnFocusChangeListener(fcl);
        setTextName.setOnFocusChangeListener(fcl);
        //setImage.setImageDrawable(drawable);
        //setTextName.setText(activeMango.getName());

        //button listener
        //Button saver = findViewById(R.id.save_return_button);
        //saver.setOnClickListener(saveButt);
    }

    protected void onSavedInstanceState(Bundle state)
    {
        EditText name = findViewById(R.id.item_name_edit);
        name.setOnFocusChangeListener(fcl);

        super.onSaveInstanceState(state);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        ArrayList<Fruits> fruitRes = data.getParcelableArrayListExtra("mangoData");
        Fruits f = fruitRes.get(0);

        super.onActivityResult(requestCode,resultCode,data);
    }

    /*View.OnClickListener saveButt = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // save data
            EditText textName = findViewById(R.id.item_name_edit);
            activeMango.setName(textName.getText().toString());
            returnToMain();
        }
    };*/

    View.OnFocusChangeListener fcl = new View.OnFocusChangeListener(){
        @Override
        public void onFocusChange(View v, boolean hasFocus)
        {
            saveFruits();
        }
    };

    @Override
    public void onBackPressed()
    {
        saveFruits();
        Intent result = new Intent();
        ArrayList<Fruits> fruits = new ArrayList<Fruits>();
        fruits.add(activeMango);
        result.putParcelableArrayListExtra("mangoData",fruits);
        result.putExtra("mangoView",getIntent().getStringExtra("mango"));
        setResult(RESULT_OK,result);
        super.onBackPressed();

    }
    public void saveFruits ()
    {
        String editName = findViewById(R.id.item_name_edit).toString();

        String editDate = findViewById(R.id.edit_date).toString();

        if(activeMango == null) {
            activeMango = new Fruits(editName, editDate);
        }
        else
        {
            activeMango.update(editName, editDate);
        }
    }

    private void restoreState(Bundle state)
    {
        if(state == null) return;

        EditText name = findViewById(R.id.item_name_edit);

    }

   /* public void returnToMain()
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("mango", activeMango);
        startActivity(intent);
    }
*/
}
