package cs.dal.weatherapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cs.dal.weatherapp.weather.ForecastEntry;
import cs.dal.weatherapp.weather.GetWeather;
import cs.dal.weatherapp.weather.WeatherForecast;

public class DetailActivity extends AppCompatActivity {

    TextView detailInfo;
    Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        /*
        *   Get and load detailed forecast from entry selected on Main Activity.
        */
        detailInfo = (TextView)findViewById(R.id.detailInfo);

        WeatherForecast currentForecast = GetWeather.getWeatherForecast();
        ForecastEntry currentEntry = currentForecast.getForecast().get(getIntent().getExtras().getInt("DETAIL_SELECTION"));

        String details = currentForecast.getLocation() + "\n\n" +
                currentEntry.getTitle().split("\\:", 2)[0] + "\n\n" +
                currentEntry.getSummary();

        detailInfo.setText(details);

        /*
        *   Font and functionality for back button to return to Main Activity.
        */
        Typeface font = Typeface.createFromAsset( getAssets(), "fontawesome-webfont.ttf" );
        backButton = (Button)findViewById(R.id.detailBackButton);
        backButton.setTypeface(font);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
