package australiancraftbeer.beerjournal.network;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import australiancraftbeer.beerjournal.network.interfaces.IBeerListEventListener;

/**
 * Created by andbreen on 29/05/2016.
 */

public class UserBeerListEventEmitter {
    IBeerListEventListener callback;
    String userId;

    ChildEventListener eventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            Log.v("UserBeerListEventEmitte", "Got key: " + dataSnapshot.getKey());
            callback.beerIdAdded(dataSnapshot.getKey());
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.v("UserBeerListEventEmitte", "Got key: " + dataSnapshot.getKey());
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.v("UserBeerListEventEmitte", "Got key: " + dataSnapshot.getKey());
            callback.beerIdRemoved(dataSnapshot.getKey());
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Log.e("Emitte", databaseError.getMessage());
        }
    };

    public UserBeerListEventEmitter(String userId, IBeerListEventListener listener) {
        this.callback = listener;
        this.userId = userId;
        Log.v("UserBeerListEventEmitte", "User id is " + userId);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users/" + userId + "/beers");
        ref.addChildEventListener(eventListener);
    }
}
