package australiancraftbeer.beerjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import australiancraftbeer.beerjournal.util.Constants;

/**
 * Created by andbreen on 22/05/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (requiresLogin() && !isLoggedIn()) {
            showLoginActivity();
        } else {
            onLoggedIn();
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

}
