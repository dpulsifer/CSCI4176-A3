package cs.dal.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView locationView;
    TextView updateView;
    WeatherForecast weatherForecast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationView = (TextView)findViewById(R.id.locationView);
        updateView = (TextView)findViewById(R.id.updateView);

        XmlPullParserFactory pullParserFactory;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = getApplicationContext().getAssets().open("source.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            weatherForecast = parseXML(parser);

            locationView.setText(weatherForecast.getLocation());
            updateView.setText(weatherForecast.getUpdateTime());

        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private WeatherForecast parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
    {
        int eventType = parser.getEventType();
        WeatherForecast newForecast = new WeatherForecast();

        boolean isEntry = false;
        String entryTitle = "";
        String entryCategory = "";
        String entrySummary = "";

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("title") && newForecast.getLocation() == null){
                        newForecast.setLocation(parser.nextText());
                    }
                    else if (name.equals("updated") && newForecast.getUpdateTime() == null) {
                        newForecast.setUpdateTime(parser.nextText());
                    }
                    else if (name.equals("entry")) {
                        isEntry = true;
                    }
                    else if ( isEntry == true ) {

                        if (name.equals("title")) { entryTitle = parser.nextText(); }
                        if (name.equals("category")) {
                            entryCategory = parser.getAttributeValue(null, "term");
                        }
                        if (name.equals("summary")) {
                            entrySummary = parser.nextText();
                            newForecast.addForecastEntry(new ForecastEntry(entryTitle.trim(), entryCategory.trim(), entrySummary.trim()));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    break;
            }
            eventType = parser.next();
        }

        return newForecast;

    }
}
