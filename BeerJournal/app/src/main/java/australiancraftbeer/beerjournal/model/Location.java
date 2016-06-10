package australiancraftbeer.beerjournal.model;

/**
 * Created by andbreen on 30/05/2016.
 */

public class Location {
    float latitude, longitude;
    String name, gPlacesId;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getgPlacesId() {
        return gPlacesId;
    }

    public void setgPlacesId(String gPlacesId) {
        this.gPlacesId = gPlacesId;
    }
}
