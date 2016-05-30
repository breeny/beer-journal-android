package australiancraftbeer.beerjournal.network;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import australiancraftbeer.beerjournal.model.Beer;
import australiancraftbeer.beerjournal.network.interfaces.IBeerEventListener;

/**
 * Created by andbreen on 22/05/2016.
 */

public class BeerEventEmitter {

    IBeerEventListener callback;

    public BeerEventEmitter(IBeerEventListener callback) {
        this.callback = callback;
    }

    public void addListener(String beerId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference beerRef = database.getReference("beers/" + beerId);
        beerRef.addValueEventListener(listener);
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Beer beer = dataSnapshot.getValue(Beer.class);
            if (beer != null) {
                callback.beerUpdated(beer);
            } else {
                callback.beerRemoved(beer);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("BeerEventEmitter", databaseError.getMessage());
        }
    };
}
