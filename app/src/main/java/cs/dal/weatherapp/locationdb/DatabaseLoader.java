package cs.dal.weatherapp.locationdb;

import android.content.Context;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by duncanpulsifer on 2017-03-13.
 */

public class DatabaseLoader {

    public static void databaseLoader(Context mContext) {
        DatabaseHandler db = new DatabaseHandler(mContext);
        if (db.doesTableExist() == true) { return; }

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(mContext.getAssets().open("feeds.csv"));
            Scanner inputStream = new Scanner(inputStreamReader);

            while (inputStream.hasNext()) {
                String data = inputStream.nextLine();
                String[] line = data.split(",");

                db.addLocation(new Location(line[1], line[0]));
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
