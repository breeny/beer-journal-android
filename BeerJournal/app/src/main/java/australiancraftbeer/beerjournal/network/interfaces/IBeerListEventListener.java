package australiancraftbeer.beerjournal.network.interfaces;

/**
 * Created by andbreen on 29/05/2016.
 */

public interface IBeerListEventListener {
    void beerIdAdded(String beerId);
    void beerIdRemoved(String beerId);
}
