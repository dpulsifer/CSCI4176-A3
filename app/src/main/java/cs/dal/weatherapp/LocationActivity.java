package cs.dal.weatherapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cs.dal.weatherapp.locationdb.DatabaseHandler;
import cs.dal.weatherapp.locationdb.Location;
import cs.dal.weatherapp.weather.GetWeather;

public class LocationActivity extends AppCompatActivity {

    EditText searchLocation;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        searchLocation = (EditText)findViewById(R.id.searchLocation);
        searchLocation.setHint("Search");
        listView = (ListView)findViewById(R.id.locationList);
        listView.setTextFilterEnabled(true);

        final DatabaseHandler db = new DatabaseHandler(this);

        List<Location> locationList = db.getAllLocations();

        final List<String> locationNameList = new ArrayList<>();
        final List<String> sortedLocationNameList = new ArrayList<>();
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
                Location foundLocation = db.getLocation(itemPosition);
                GetWeather.setLocationXML(foundLocation.get_url());

                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}