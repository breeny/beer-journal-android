package australiancraftbeer.beerjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.logging.Level;

import australiancraftbeer.beerjournal.model.User;
import australiancraftbeer.beerjournal.util.Constants;

/**
 * Created by andbreen on 22/05/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);

        checkIfLoginIsRequired();
    }

    void checkIfLoginIsRequired() {
        if (requiresLogin() && !isLoggedIn()) {
            showLoginActivity();
        } else {
            if (User.currentUser() != null) {
                onLoggedIn();
            } else {
                initialiseUser();
            }
        }
    }

    protected abstract boolean requiresLogin();

    protected abstract void onLoggedIn();

    private boolean isLoggedIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        return auth.getCurrentUser() != null;
    }

    private void showLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, Constants.LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.SUCCESS) {
            if (requestCode == Constants.LOGIN) {
                onLoggedIn();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initialiseUser() {
        FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User.initialise(dataSnapshot.getValue(User.class));
                onLoggedIn();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("User retrieval", databaseError.getMessage());
            }
        });
    }

}
