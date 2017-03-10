package cs.dal.weatherapp;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cs.dal.weatherapp.locationdb.DatabaseHandler;
import cs.dal.weatherapp.locationdb.Location;

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

        DatabaseHandler db = new DatabaseHandler(this);

        List<Location> locationList = db.getAllLocations();
        List<String> locationNameList = new ArrayList<>();
        for (int i = 0; i < locationList.size(); i++) {
            System.out.println(locationList.get(i).get_location());
            locationNameList.add(locationList.get(i).get_location());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, locationNameList );

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
    }
}
