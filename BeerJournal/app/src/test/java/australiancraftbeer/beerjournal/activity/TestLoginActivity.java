package australiancraftbeer.beerjournal.activity;

import android.os.Build;
import android.widget.EditText;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.TestLifecycleApplication;
import org.robolectric.annotation.Config;

import java.lang.reflect.Method;

import australiancraftbeer.beerjournal.Application;
import australiancraftbeer.beerjournal.BuildConfig;
import australiancraftbeer.beerjournal.LoginActivity;
import australiancraftbeer.beerjournal.R;
import australiancraftbeer.beerjournal.application.SuccessfulLoginApplication;
import australiancraftbeer.beerjournal.injection.TestAuthComponent;
import australiancraftbeer.beerjournal.injection.DaggerAuthComponent;
import australiancraftbeer.beerjournal.model.User;

import static org.junit.Assert.*;

/**
 * Created by andbreen on 11/06/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23, application = SuccessfulLoginApplication.class, manifest = "src/main/AndroidManifest.xml")
public class TestLoginActivity {

    @Test
    public void enteringEmailAndPasswordShouldLogin() {
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        ((EditText)activity.findViewById(R.id.email)).setText("test-user@test.com");
        ((EditText)activity.findViewById(R.id.password)).setText("password");
        activity.findViewById(R.id.login).performClick();

        assertTrue(User.currentUser() != null);
    }

}
