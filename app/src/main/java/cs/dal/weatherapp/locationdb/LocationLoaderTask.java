package cs.dal.weatherapp.locationdb;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by duncanpulsifer on 2017-03-09.
 */

public class LocationLoaderTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;

    public LocationLoaderTask(Context context) {

        this.mContext = context;

    }

    //check if locations database table has been created. if yes, returns.
    //if not, loads locations and urls from source file
    @Override
    protected Void doInBackground(Void... params) {
        DatabaseHandler db = new DatabaseHandler(mContext);
        if (db.doesTableExist() == true) { return null; }

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
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
