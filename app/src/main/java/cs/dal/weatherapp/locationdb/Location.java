package cs.dal.weatherapp.locationdb;

/**
 * Created by duncanpulsifer on 2017-03-09.
 */

public class Location {

    int _id;
    String _location;
    String _url;

    public Location() {}

    public Location(int id, String location, String url) {
        this._id = id;
        this._location = location;
        this._url = url;
    }

    public Location(String location, String url) {
        this._location = location;
        this._url = url;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_location() {
        return _location;
    }

    public void set_location(String _location) {
        this._location = _location;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }
}
