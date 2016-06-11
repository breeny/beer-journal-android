package australiancraftbeer.beerjournal.stubs;

import java.util.HashMap;

import australiancraftbeer.beerjournal.model.User;
import australiancraftbeer.beerjournal.network.interfaces.IAuthenticationCallback;
import australiancraftbeer.beerjournal.network.interfaces.IAuthenticationProvider;

/**
 * Created by andbreen on 11/06/2016.
 */

public class TestAuthProvider implements IAuthenticationProvider {

    IAuthenticationCallback callback;

    @Override
    public void setCallback(IAuthenticationCallback callback) {
        this.callback = callback;
    }

    @Override
    public Boolean isLoggedIn() {
        return true;
    }

    @Override
    public void logIn(String userName, String password) {
        User user = new User();
        user.setEmail("test@test.com");
        user.setId("test-user");
        user.setJournals(new HashMap<String, String>());

        callback.onLoginCallback(user);
    }

    @Override
    public void logOut() {

    }
}
