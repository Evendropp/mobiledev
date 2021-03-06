package au.edu.swin.sdmd.suncalculatorjava;

import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocationList extends AppCompatActivity {

    private RecyclerView locationList;
    private RecyclerView.Adapter listAdapter;
    private RecyclerView.LayoutManager listLayoutMan;
    private HashMap<String, android.location.Location>mlocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        initUI();
    }

    public void initUI()
    {
        //set up recycler list
        locationList = findViewById(R.id.location_list);
        mlocations = loadLocationData();
        ArrayList<String> locations = new ArrayList<>(mlocations.keySet());

        listLayoutMan = new LinearLayoutManager(this);
        locationList.setLayoutManager(listLayoutMan);

        listAdapter = new listAdpt(locations);
        locationList.setAdapter(listAdapter);

        //set up custom button add
        Button addButton = findViewById(R.id.add_custom_location);
        addButton.setOnClickListener(addCustomButton);
    }

    View.OnClickListener addCustomButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), AddCustomLocation.class);
            startActivity(intent);
        }
    };

    //list adapter for recyclerView
    public class listAdpt extends RecyclerView.Adapter<listAdpt.ViewHolder>
    {
        List<String>mdata;

        class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView nameText;
            public TextView latText;
            public TextView longText;

            public ViewHolder(View v)
            {
                super(v);
                nameText = v.findViewById(R.id.location);
                latText = v.findViewById(R.id.latitude);
                longText = v.findViewById(R.id.longitude);
            }
        }


        public listAdpt (List<String> data)
        {
            mdata = data;
        }

        @NonNull
        @Override
        public listAdpt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
            v.setOnClickListener(clickList);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull listAdpt.ViewHolder holder, int i) {
            String name = mdata.get(i);
            holder.nameText.setText(name);

            holder.longText.setText("Longitude: " + String.format("%3.3f",mlocations.get(name).getLongitude()));
            holder.latText.setText("Latitude: " + String.format("%3.3f",mlocations.get(name).getLatitude()));
        }

        @Override
        public int getItemCount() {
            return mdata.size();
        }

        View.OnClickListener clickList = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                int i = locationList.getChildLayoutPosition(v);
               /* Log.i("position_clicked",String.valueOf(i));*/

                String name = mdata.get(i);
                /*Log.i("name_grab",name);*/
                intent.putExtra("name",name);
                setResult(RESULT_OK,intent);
                finish();
            }
        };
    }

    @Override
    public void onResume()
    {
        initUI();
        super.onResume();
    }



    // location data setup
    private HashMap<String, android.location.Location> loadLocationData(){
        HashMap<String, android.location.Location> locations = new HashMap<>();

        {
            BufferedReader b = null;
            try {
                //get data from file
                /*b = new BufferedReader(new InputStreamReader(getAssets().open("au_locations.txt")));*/
                b = new BufferedReader(new InputStreamReader(openFileInput("custom_locations.txt")));
            }
            catch (IOException e)
            {
                Log.i("io Exception", "can't find location data file");
            }
            String currentLine;
            ArrayList<String> fileData = new ArrayList<String>();
            try{
                if (b != null) {
                    while ((currentLine = b.readLine())!= null)
                    {
                        fileData.add(currentLine);
                        Log.i("added_locationName",currentLine);
                    }
                }

            }
            catch(IOException e)
            {
                Log.i("io Exception", "got null in file");
            }
            for(String s : fileData)
            {
                String trimdata = s.trim();
                if(!trimdata.equals("")) {
                    String[] data = s.split(",");
                    String cityName = data[0];
                    Double latitude = Double.parseDouble(data[1]);
                    Double longitude = Double.parseDouble(data[2]);
                    android.location.Location locData = new android.location.Location("");
                    locData.setLatitude(latitude);
                    locData.setLongitude(longitude);
                    locations.put(cityName, locData);
                }
            }
        }
        return locations;
    }

}
