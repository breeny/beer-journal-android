package australiancraftbeer.beerjournal.application;

import australiancraftbeer.beerjournal.Application;
import australiancraftbeer.beerjournal.injection.DaggerAuthComponent;

/**
 * Created by andbreen on 11/06/2016.
 */

public class SuccessfulLoginApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        this.authComponent = DaggerAuthComponent.builder().build();
    }

}
