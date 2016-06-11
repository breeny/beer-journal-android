package australiancraftbeer.beerjournal.injection;

import australiancraftbeer.beerjournal.network.AuthenticationProvider;
import australiancraftbeer.beerjournal.network.interfaces.IAuthenticationProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by andbreen on 11/06/2016.
 */

@Module
public class AuthModule {

    @Provides
    IAuthenticationProvider provideAuthentication() {
        return new AuthenticationProvider();
    }
}
