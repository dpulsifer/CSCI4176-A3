package cs.dal.weatherapp.weather;

/**
 * Created by duncanpulsifer on 2017-03-08.
 */

public class ForecastEntry {

    String title;
    String category;
    String summary;

    public ForecastEntry(String title, String category, String summary){
        this.title = title;
        this.category = category;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getSummary() {
        return summary;
    }
}
