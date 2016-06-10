package australiancraftbeer.beerjournal;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import australiancraftbeer.beerjournal.adapters.JournalSelectionAdapter;
import australiancraftbeer.beerjournal.model.Beer;
import australiancraftbeer.beerjournal.model.Location;
import australiancraftbeer.beerjournal.model.User;
import australiancraftbeer.beerjournal.util.Constants;

public class EditBeerActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {

    private EditText name, brewery, style, notes;
    private RatingBar rating;
    private Button placeButton;
    private GoogleMap googleMap;
    private View frameLayout;
    private Place location;
    private RecyclerView journalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_beer);
        initialiseUi();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Create New Beer");

        GoogleApiClient mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
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
        rating = (RatingBar) findViewById(R.id.review);
        placeButton = (Button) findViewById(R.id.findPlace);
        frameLayout = findViewById(R.id.mapWrapper);

        journalList = (RecyclerView) findViewById(R.id.recyclerView);
        JournalSelectionAdapter adapter = new JournalSelectionAdapter(User.currentUser().getJournals());
        journalList.setAdapter(adapter);
        journalList.setLayoutManager(new GridLayoutManager(this, 2));
    }

    void saveNewBeer() {
        if (validateInput()) {
            Beer beer = new Beer();
            beer.setName(name.getText().toString());
            beer.setBrewery(brewery.getText().toString());
            beer.setRating(rating.getRating());

            if (this.location != null) {
                Location location = new Location();
                location.setLatitude((float)this.location.getLatLng().latitude);
                location.setLongitude((float)this.location.getLatLng().longitude);
                location.setName(this.location.getName().toString());
                location.setgPlacesId(this.location.getId());
                beer.setLocation(location);
            }

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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void onSelectLocation(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), Constants.PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                placeButton.setText(place.getName() + "\nSelect to change to a nearby location");
                this.location = place;
                if (googleMap != null) {
                    MarkerOptions options = new MarkerOptions();
                    options.draggable(false);
                    options.position(place.getLatLng());
                    googleMap.addMarker(options);

                    UiSettings mapSettings = googleMap.getUiSettings();
                    mapSettings.setAllGesturesEnabled(false);

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 15));

                    frameLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
