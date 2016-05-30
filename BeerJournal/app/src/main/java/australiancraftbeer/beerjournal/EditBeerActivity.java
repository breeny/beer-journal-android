package australiancraftbeer.beerjournal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import australiancraftbeer.beerjournal.model.Beer;

public class EditBeerActivity extends AppCompatActivity {

    EditText name, brewery, style, notes, hadAt;
    RatingBar rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_beer);
        initialiseUi();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Create New Beer");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_beer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id) {
            case R.id.done :
                saveNewBeer();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void initialiseUi() {
        name = (EditText) findViewById(R.id.beer);
        brewery = (EditText) findViewById(R.id.brewery);
        style = (EditText) findViewById(R.id.style);
        notes = (EditText) findViewById(R.id.notes);
        hadAt = (EditText) findViewById(R.id.hadAt);
        rating = (RatingBar) findViewById(R.id.review);
    }

    void saveNewBeer() {
        if (validateInput()) {
            Beer beer = new Beer();
            beer.setName(name.getText().toString());
            beer.setBrewery(name.getText().toString());
            beer.setRating(rating.getRating());


            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference beerRef = database.getReference("beers").push();

            beer.setId(beerRef.getKey());

            beerRef.setValue(beer);

            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference userBeerRef = database.getReference("users/" +userId + "/beers/" + beerRef.getKey());
            userBeerRef.setValue(true);

            finish();
        }
    }

    boolean validateInput() {
        boolean valid = true;

        if (name.getText().toString().equals("")) {
            valid = false;
        }

        return valid;
    }
}
