package cs.dal.weatherapp.locationdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duncanpulsifer on 2017-03-09.
 */

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "locationManager";

    private static final String TABLE_LOCATIONS = "locations";
    private static final String KEY_ID = "id";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_URL = "url";

    private static final String TABLE_CURRENT = "current";
    private static final String KEY_CITYID = "id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LOCATION + " TEXT,"
                + KEY_URL + " TEXT" + ")";
        String CREATE_CURRENT_TABLE = "CREATE TABLE " + TABLE_CURRENT + "(" + KEY_CITYID + ")";
        db.execSQL(CREATE_LOCATIONS_TABLE);
        db.execSQL(CREATE_CURRENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENT);
        onCreate(db);
    }

    //check if Locations table exists
    public boolean doesTableExist() {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_LOCATIONS;

        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if(icount>0)
            return true;
        else
            return false;
    }

    //add location and url to database
    public void addLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_LOCATION, location.get_location());
        values.put(KEY_URL, location.get_url());

        db.insert(TABLE_LOCATIONS, null, values);
        db.close();
    }

    //get individual location and url based on id
    public Location getLocation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LOCATIONS, new String[] { KEY_ID,
        KEY_LOCATION, KEY_URL }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) { cursor.moveToFirst(); }

        Location location = new Location(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return location;
    }

    //get all locations and urls in a list
    public List<Location> getAllLocations() {
        List<Location> locationList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_LOCATIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.set_id(Integer.parseInt(cursor.getString(0)));
                location.set_location(cursor.getString(1));
                location.set_url(cursor.getString(2));

                locationList.add(location);
            } while (cursor.moveToNext());
        }

        return locationList;
    }

    //set current location selection
    public void setCurrent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CURRENT, null,null);

        ContentValues values = new ContentValues();
        values.put(KEY_CITYID, Integer.toString(id));

        db.insert(TABLE_CURRENT, null, values);
        db.close();
    }

    //get current location selection
    public int getCurrent() {
        SQLiteDatabase db = this.getWritableDatabase();
        int locationID = -1;
        Cursor cursor = db.query(TABLE_CURRENT, new String[] { "*" },
                null, null, null, null, null);
        if(cursor != null)
        {
            if (cursor.moveToFirst()) {
                locationID = Integer.parseInt(cursor.getString(0)); // DeviceID
            }
        }
        cursor.close();
        db.close();
        return locationID;
    }

    //check if current location has been set
    public boolean isCurrentSet() {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT count(*) FROM " + TABLE_CURRENT;

        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if(icount>0)
            return true;
        else
            return false;
    }
}
