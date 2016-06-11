package australiancraftbeer.beerjournal.network.interfaces;

import australiancraftbeer.beerjournal.error.exception.AuthenticationException;

/**
 * Created by andbreen on 10/06/2016.
 */

public interface IAuthenticationProvider {
    void setCallback(IAuthenticationCallback callback);
    Boolean isLoggedIn();
    void logIn(String userName, String password);
    void logOut();

}
