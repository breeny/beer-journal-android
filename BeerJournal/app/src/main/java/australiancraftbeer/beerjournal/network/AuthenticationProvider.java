package australiancraftbeer.beerjournal.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import australiancraftbeer.beerjournal.Application;
import australiancraftbeer.beerjournal.error.exception.AuthenticationException;
import australiancraftbeer.beerjournal.model.User;
import australiancraftbeer.beerjournal.network.interfaces.IAuthenticationCallback;
import australiancraftbeer.beerjournal.network.interfaces.IAuthenticationProvider;

/**
 * Created by andbreen on 10/06/2016.
 */

public class AuthenticationProvider implements IAuthenticationProvider {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    IAuthenticationCallback callback;

    public void setCallback(IAuthenticationCallback callback) {
        this.callback = callback;
    }

    @Override
    public Boolean isLoggedIn() {
        return auth.getCurrentUser() != null;
    }

    @Override
    public void logIn(String userName, String password) {
        auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseDatabase.getInstance().getReference("users/" + auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            callback.onLoginCallback(user);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                } else {
                    //Handle error
                    Log.e("Login", task.getException().getMessage());
                }
            }
        });
    }

    @Override
    public void logOut() {
        auth.signOut();
    }
}
