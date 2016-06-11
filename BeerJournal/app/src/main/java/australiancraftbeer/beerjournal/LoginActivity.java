package australiancraftbeer.beerjournal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import australiancraftbeer.beerjournal.model.User;
import australiancraftbeer.beerjournal.network.interfaces.IAuthenticationCallback;
import australiancraftbeer.beerjournal.network.interfaces.IAuthenticationProvider;
import australiancraftbeer.beerjournal.util.Constants;

public class LoginActivity extends AppCompatActivity implements IAuthenticationCallback {

    @Inject
    IAuthenticationProvider authProvider;

    EditText password, email;
    Button onLogin;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        onLogin = (Button) findViewById(R.id.email_sign_in_button);

        onLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });

        ((Application) getApplication()).getAuthComponent().inject(this);
        authProvider.setCallback(this);
    }

    void onLogin() {
        progress = new ProgressDialog(this);
        progress.setIndeterminate(true);
        progress.setMessage("Logging in");
        progress.setCancelable(false);
        progress.show();

        authProvider.logIn(email.getText().toString(), password.getText().toString());
    }

    @Override
    public void onLoginCallback(User user) {
        User.initialise(user);
        progress.dismiss();
        setResult(Constants.SUCCESS);
        finish();
    }

    @Override
    public void onRegisterCallback() {

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
