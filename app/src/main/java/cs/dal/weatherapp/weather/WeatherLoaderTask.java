package cs.dal.weatherapp.weather;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import cs.dal.weatherapp.locationdb.DatabaseHandler;

/**
 * Created by duncanpulsifer on 2017-03-09.
 */

public class WeatherLoaderTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;
    private String xmlAddress;

    public WeatherLoaderTask(Context context) {

        this.mContext = context;

    }

    @Override
    protected Void doInBackground(Void... params) {

        DatabaseHandler db = new DatabaseHandler(mContext);
        XmlPullParserFactory pullParserFactory;

        if ((db.isCurrentSet()) && (db.getCurrent() != -1)) {
            GetWeather.setLocationXML(db.getLocation(db.getCurrent()).get_url());
        }
        if (GetWeather.getLocationXML() == null) { return null; }
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = new URL(GetWeather.getLocationXML()).openStream();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            GetWeather.setWeatherForecast(parseXML(parser));

        } catch (XmlPullParserException e) {

            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

    public static WeatherForecast parseXML(XmlPullParser parser) throws XmlPullParserException,IOException
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
