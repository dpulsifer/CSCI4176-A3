package cs.dal.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cs.dal.weatherapp.weather.ForecastEntry;
import cs.dal.weatherapp.weather.GetWeather;
import cs.dal.weatherapp.weather.WeatherForecast;

public class DetailActivity extends AppCompatActivity {

    TextView detailLocation;
    TextView detailCategory;
    TextView detailTitle;
    TextView detailInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailLocation = (TextView)findViewById(R.id.detailLocation);
        detailCategory = (TextView)findViewById(R.id.detailCategory);
        detailTitle = (TextView)findViewById(R.id.detailTitle);
        detailInfo = (TextView)findViewById(R.id.detailInfo);

        WeatherForecast currentForecast = GetWeather.getWeatherForecast();
        ForecastEntry currentEntry = currentForecast.getForecast().get(getIntent().getExtras().getInt("DETAIL_SELECTION"));

        detailLocation.setText(currentForecast.getLocation());
        detailCategory.setText(currentEntry.getCategory());
        detailTitle.setText(currentEntry.getTitle());
        detailInfo.setText(currentEntry.getSummary());

    }
}
