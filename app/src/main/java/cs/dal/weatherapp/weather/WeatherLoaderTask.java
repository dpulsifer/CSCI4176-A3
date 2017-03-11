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
        } else { return null; }
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            InputStream in_s = new URL(GetWeather.getLocationXML()).openStream();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);

            GetWeather.setWeatherForecast(ParseWeatherXML.parseXML(parser));

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

}
