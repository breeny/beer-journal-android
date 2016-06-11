package australiancraftbeer.beerjournal.network.interfaces;

import australiancraftbeer.beerjournal.model.User;

/**
 * Created by andbreen on 11/06/2016.
 */

public interface IAuthenticationCallback {
    void onLoginCallback(User user);
    void onRegisterCallback();
}
