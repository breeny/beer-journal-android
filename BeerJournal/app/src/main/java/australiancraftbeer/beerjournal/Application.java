package australiancraftbeer.beerjournal;

import australiancraftbeer.beerjournal.injection.AuthComponent;
import australiancraftbeer.beerjournal.injection.DaggerAuthComponent;

/**
 * Created by andbreen on 11/06/2016.
 */

public class Application extends android.app.Application {

    protected AuthComponent authComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        authComponent = DaggerAuthComponent.builder().build();
    }

    public AuthComponent getAuthComponent() {
        return authComponent;
    }
}
