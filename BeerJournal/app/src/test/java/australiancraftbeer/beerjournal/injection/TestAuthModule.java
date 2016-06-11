package australiancraftbeer.beerjournal.injection;

import australiancraftbeer.beerjournal.network.interfaces.IAuthenticationProvider;
import australiancraftbeer.beerjournal.stubs.TestAuthProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Created by andbreen on 11/06/2016.
 */

@Module
public class TestAuthModule {

    @Provides
    IAuthenticationProvider provideAuthentication() {
        return new TestAuthProvider();
    }
}
