package cs.dal.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    TextView locationView;
    TextView updateView;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationView = (TextView)findViewById(R.id.locationView);
        updateView = (TextView)findViewById(R.id.updateView);
        listView = (ListView)findViewById(R.id.listView);

        GetWeather.setLocationXML("http://weather.gc.ca/rss/city/ns-19_e.xml");

        new WeatherLoaderTask(MainActivity.this).execute();

        try{ Thread.sleep(500); }catch(InterruptedException e){ }

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




    }


}
