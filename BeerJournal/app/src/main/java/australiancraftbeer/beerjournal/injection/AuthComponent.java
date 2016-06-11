package australiancraftbeer.beerjournal.injection;

import australiancraftbeer.beerjournal.LoginActivity;
import australiancraftbeer.beerjournal.MainActivity;
import dagger.Component;

/**
 * Created by andbreen on 11/06/2016.
 */
@Component(modules={AuthModule.class})
public interface AuthComponent {

    void inject(LoginActivity activity);
    void inject(MainActivity activity);
}

