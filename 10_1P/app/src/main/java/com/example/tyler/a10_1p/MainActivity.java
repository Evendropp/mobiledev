package com.example.tyler.a10_1p;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(buttlistener);
    }

    View.OnClickListener buttlistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            new updateText().execute();
        }
    };

    private class updateText extends AsyncTask<Void, Integer, Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 3; i >= 0; i--) {
                    Thread.sleep(1000);
                    publishProgress(i);
                    Log.i("count",Integer.toString(i));
                }
            } catch (InterruptedException ie)

            {
                ie.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            TextView text = findViewById(R.id.textView);
            text.setText(Integer.toString(values[0]));
            super.onProgressUpdate(values);
        }
    }
}
