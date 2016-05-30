package australiancraftbeer.beerjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import australiancraftbeer.beerjournal.util.Constants;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
    }

    public void onLogin(View view) {
    }

    public void onRegister(View view) {
        startActivityForResult(new Intent(this, RegisterActivity.class), Constants.REGISTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Constants.SUCCESS) {
            if (requestCode == Constants.REGISTER) {
                setResult(Constants.SUCCESS);
                finish();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
