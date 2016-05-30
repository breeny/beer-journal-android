package australiancraftbeer.beerjournal.network.interfaces;

import australiancraftbeer.beerjournal.model.Beer;

/**
 * Created by andbreen on 22/05/2016.
 */

public interface IBeerEventListener {
    void beerUpdated(Beer beer);
    void beerRemoved(Beer beer);
}
