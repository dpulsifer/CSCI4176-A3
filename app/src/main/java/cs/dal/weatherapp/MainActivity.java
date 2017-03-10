package cs.dal.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import cs.dal.weatherapp.locationdb.DatabaseHandler;
import cs.dal.weatherapp.locationdb.LocationLoaderTask;
import cs.dal.weatherapp.weather.GetWeather;
import cs.dal.weatherapp.weather.WeatherForecast;
import cs.dal.weatherapp.weather.WeatherLoaderTask;

public class MainActivity extends AppCompatActivity {

    Button locationButton;
    TextView locationView;
    TextView updateView;
    ListView listView;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationButton = (Button)findViewById(R.id.locationButton);
        locationView = (TextView)findViewById(R.id.locationView);
        updateView = (TextView)findViewById(R.id.updateView);
        listView = (ListView)findViewById(R.id.listView);

        GetWeather.setLocationXML("http://weather.gc.ca/rss/city/ns-19_e.xml");

        new LocationLoaderTask(MainActivity.this).execute();
        new WeatherLoaderTask(MainActivity.this).execute();

        while (GetWeather.getWeatherForecast() == null) {
            try{ Thread.sleep(250); }catch(InterruptedException e){ }
        }

        if (GetWeather.getWeatherForecast() != null) {

            WeatherForecast currentForecast = GetWeather.getWeatherForecast();
            locationView.setText(currentForecast.getLocation());
            updateView.setText(currentForecast.getUpdateTime());

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentForecast.getShortForecast());
            listView.setAdapter(adapter);

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

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });

    }

}
