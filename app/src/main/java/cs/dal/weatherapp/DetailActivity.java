package cs.dal.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cs.dal.weatherapp.weather.ForecastEntry;
import cs.dal.weatherapp.weather.GetWeather;
import cs.dal.weatherapp.weather.WeatherForecast;

public class DetailActivity extends AppCompatActivity {

    TextView detailLocation;
    TextView detailTitle;
    TextView detailInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailLocation = (TextView)findViewById(R.id.detailLocation);
        detailTitle = (TextView)findViewById(R.id.detailTitle);
        detailInfo = (TextView)findViewById(R.id.detailInfo);

        WeatherForecast currentForecast = GetWeather.getWeatherForecast();
        ForecastEntry currentEntry = currentForecast.getForecast().get(getIntent().getExtras().getInt("DETAIL_SELECTION"));

        detailLocation.setText(currentForecast.getLocation());
        detailTitle.setText(currentEntry.getTitle().split("\\:", 2)[0]);
        detailInfo.setText(currentEntry.getSummary());

    }
}
