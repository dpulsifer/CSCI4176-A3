package cs.dal.weatherapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs.dal.weatherapp.locationdb.DatabaseHandler;
import cs.dal.weatherapp.locationdb.Location;
import cs.dal.weatherapp.locationdb.LocationLoaderTask;
import cs.dal.weatherapp.weather.GetWeather;
import cs.dal.weatherapp.weather.WeatherLoaderTask;

public class LocationActivity extends AppCompatActivity {

    Button backButton;
    EditText searchLocation;
    TextView currentLocation;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );

        backButton = (Button)findViewById(R.id.backButton);
        backButton.setTypeface(font);
        searchLocation = (EditText)findViewById(R.id.searchLocation);
        searchLocation.setHint("Search");
        currentLocation = (TextView)findViewById(R.id.currentLocation);
        if (GetWeather.getLocationName() != null) {
            currentLocation.setText("Current Location: " + GetWeather.getLocationName());
        }
        listView = (ListView)findViewById(R.id.locationList);
        listView.setTextFilterEnabled(true);

        final DatabaseHandler db = new DatabaseHandler(this);

        List<Location> locationList = db.getAllLocations();
        final List<String> locationNameList = new ArrayList<>();
        final List<String> sortedLocationNameList = new ArrayList<>();

        // create separate sorted list and leave original intact to all easier database access
        for (int i = 0; i < locationList.size(); i++) {
            locationNameList.add(locationList.get(i).get_location());
            sortedLocationNameList.add(locationList.get(i).get_location());
        }
        Collections.sort(sortedLocationNameList, String.CASE_INSENSITIVE_ORDER);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, sortedLocationNameList );

        listView.setAdapter(arrayAdapter);

        searchLocation.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = locationNameList.indexOf(arrayAdapter.getItem(position)) + 1;
                db.setCurrent(itemPosition);
                Location foundLocation = db.getLocation(itemPosition);
                GetWeather.setLocationXML(foundLocation.get_url());
                GetWeather.setLocationName(foundLocation.get_location());

                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
