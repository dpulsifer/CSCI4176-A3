package cs.dal.weatherapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cs.dal.weatherapp.locationdb.DatabaseLoader;
import cs.dal.weatherapp.weather.GetWeather;
import cs.dal.weatherapp.weather.WeatherForecast;
import cs.dal.weatherapp.weather.WeatherLoaderTask;

public class MainActivity extends AppCompatActivity {

    TextView locationForecast;
    Button locationButton;
    TextView updateView;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseLoader.databaseLoader(MainActivity.this);
        new WeatherLoaderTask(MainActivity.this).execute();

        locationForecast = (TextView)findViewById(R.id.locationForecast);

        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        locationButton = (Button)findViewById(R.id.locationButton);
        locationButton.setTypeface(font);

        updateView = (TextView)findViewById(R.id.updateView);
        listView = (ListView)findViewById(R.id.listView);

        /*
        *   If location not set, show toast to request location. Else, display current location,
        *   update time, and summary forecast.
        */
        if (GetWeather.getLocationXML() == null ) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please select a location.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        } else {

            try{ Thread.sleep(500); }catch(InterruptedException e){ }

            locationForecast.setText(GetWeather.getLocationName());
            WeatherForecast currentForecast = GetWeather.getWeatherForecast();
            String updateViewText = "Update Time: " + currentForecast.getUpdateTime().replace("T", " ").replace("Z", "");
            updateView.setText(updateViewText);

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, GetWeather.getWeatherForecast().getShortForecast());
            listView.setAdapter(adapter);

            /*
            *   Handle click of item in summary forecast list by passing index of list selection to
            *   Detail Activity.
            */
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("DETAIL_SELECTION", position);
                    startActivity(intent);
                }
            });
        }

        /*
        *   Handle click of location button on Main Activity.
        */
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

    }

}
