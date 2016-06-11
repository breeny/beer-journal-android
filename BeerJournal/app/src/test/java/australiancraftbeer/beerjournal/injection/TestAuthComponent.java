package australiancraftbeer.beerjournal.injection;

import australiancraftbeer.beerjournal.LoginActivity;
import australiancraftbeer.beerjournal.injection.AuthComponent;
import dagger.Component;

/**
 * Created by andbreen on 11/06/2016.
 */

@Component(modules={TestAuthModule.class})
public interface TestAuthComponent extends AuthComponent {

    void inject(LoginActivity activity);

}

