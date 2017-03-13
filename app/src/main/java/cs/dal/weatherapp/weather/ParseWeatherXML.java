package cs.dal.weatherapp.weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by duncanpulsifer on 2017-03-10.
 * Parses an Environment Canada forecast XML file.
 */

public class ParseWeatherXML {

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
                            entrySummary = parser.nextText().replace("<b>", "").replace("</b>", "").replace("<br/>", "");
                            entrySummary = entrySummary.replace("&", " ").replace(";", " ");
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
