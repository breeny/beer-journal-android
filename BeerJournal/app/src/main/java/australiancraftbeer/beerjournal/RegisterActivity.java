package australiancraftbeer.beerjournal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import australiancraftbeer.beerjournal.model.User;
import australiancraftbeer.beerjournal.util.Constants;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password, passwordConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Register");

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        passwordConfirm = (EditText) findViewById(R.id.confirm_password);
    }

    public void onRegister(View view) {
        OnCompleteListener<AuthResult> onRegisterResult = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.e("RegisterActivity", task.getException().getMessage());
                } else {
                    String userId = task.getResult().getUser().getUid();
                    FirebaseDatabase db = FirebaseDatabase.getInstance();

                    User user = new User();
                    user.setName(name.getText().toString());
                    user.setEmail(email.getText().toString());
                    user.setId(userId);

                    db.getReference("users").child(userId).setValue(user);
                    setResult(Constants.SUCCESS);
                    finish();
                }
            }
        };

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, onRegisterResult);
    }
}
