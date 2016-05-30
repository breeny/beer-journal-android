package australiancraftbeer.beerjournal.model;

import java.util.Date;

/**
 * Created by andbreen on 22/05/2016.
 */

public class Beer {
    String name, brewery, id;

    public void setName(String name) {
        this.name = name;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public String getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    double rating, price;

    public String getName() {
        return name;
    }

    Date hadTime;

    public void setHadTime(Date hadTime) {
        this.hadTime = hadTime;
    }
}
