package com.mysports.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.github.jksiezni.permissive.PermissionsGrantedListener;
import com.github.jksiezni.permissive.PermissionsRefusedListener;
import com.github.jksiezni.permissive.Permissive;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.mysports.R;
import com.mysports.custom.MyArrayAdapter;
import com.mysports.utilities.LocationTrack;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VenueProviderActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    AppCompatEditText mInputName;
    TextInputLayout mInputTextNameLayout;
    TextInputLayout mInputTextEmailLayout;
    TextInputLayout mInputTextContactPerson;
    TextInputLayout mInputTextWebsite;
    TextInputLayout mInputTextMobile;
    TextInputLayout mInputTextPhone;
    public static final String VENUEPARAMS = "venue_params";
    AppCompatSpinner mStateSpinner;
    ArrayList<String> mStateSpinnerData;
    LinearLayout mCurrentLocation;
    GoogleApiClient googleApiClient;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;
    private Location mylocation;
    Toolbar mVenueProvider;
    ImageView mVenueBackBtn;
    AppCompatEditText mEmailId;
    AppCompatEditText mContactPerson;
    AppCompatEditText mWebsiteAddress;
    AppCompatEditText mMobile;
    AppCompatEditText mPhone;
    RelativeLayout mSubmitBtn;
    String currentSate;
    Double latitude = 0d;
    Double longitude = 0d;
    String validationName;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_provider);
        mStateSpinner = findViewById(R.id.spinner_state);
        mCurrentLocation = findViewById(R.id.current_location);
        mCurrentLocation.setOnClickListener(this);
        stateDataLoading();

        mInputTextNameLayout = findViewById(R.id.input_layout_name);
        mInputTextEmailLayout = findViewById(R.id.input_layout_email);
        mInputTextContactPerson = findViewById(R.id.input_layout_contact_person);
        mInputTextWebsite = findViewById(R.id.input_layout_website_address);
        mInputTextMobile = findViewById(R.id.input_layout_mobile);
        mInputTextPhone = findViewById(R.id.input_layout_phone);

        Bundle bundle = getIntent().getExtras();
        String intentType = bundle.getString(VENUEPARAMS);
        mVenueProvider = findViewById(R.id.venue_provider_toolbar);
        TextView venueProviderTitle = mVenueProvider.findViewById(R.id.venue_title);
        mVenueBackBtn = mVenueProvider.findViewById(R.id.venue_back_btn);
        mVenueBackBtn.setOnClickListener(this);
        mSubmitBtn = findViewById(R.id.submit_btn);
        mSubmitBtn.setOnClickListener(this);
        ColorStateList csl = new ColorStateList(new int[][]{new int[0]}, new int[]{0xffE1E1E3});//hexadecimal number 0xAARRBBGG

        mInputName = findViewById(R.id.input_name);
        mInputName.setSupportBackgroundTintList(csl);// need to unit test
        mInputName.addTextChangedListener(new MyTextWatcher(mInputName));

        mEmailId = findViewById(R.id.input_email);
        mEmailId.setSupportBackgroundTintList(csl);
        mEmailId.addTextChangedListener(new MyTextWatcher(mEmailId));

        mContactPerson = findViewById(R.id.input_contact_person);
        mContactPerson.setSupportBackgroundTintList(csl);
        mContactPerson.addTextChangedListener(new MyTextWatcher(mContactPerson));

        mWebsiteAddress = findViewById(R.id.input_website_address);
        mWebsiteAddress.setSupportBackgroundTintList(csl);
        mWebsiteAddress.addTextChangedListener(new MyTextWatcher(mWebsiteAddress));

        mMobile = findViewById(R.id.input_mobile);
        mMobile.setSupportBackgroundTintList(csl);
        mMobile.addTextChangedListener(new MyTextWatcher(mMobile));

        mPhone = findViewById(R.id.input_phone);
        mPhone.setSupportBackgroundTintList(csl);
        mPhone.addTextChangedListener(new MyTextWatcher(mPhone));
        if (!intentType.equals("")) {
            if (intentType.equals("venue")) {
                mInputTextNameLayout.setHint("Name of Venue");
                venueProviderTitle.setText("Venue Provider");
                validationName = "Venue";
            }
            if (intentType.equals("sports_store")) {
                mInputTextNameLayout.setHint("Name of Store");
                venueProviderTitle.setText("Store Owner");
                validationName = "Store";
            }
            if (intentType.equals("training_academics")) {
                mInputTextNameLayout.setHint("Name of Academy");
                venueProviderTitle.setText("Training Academy");
                validationName = "Academy";
            }
        }
    }

    private synchronized void setUpGClient() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, 0, this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            googleApiClient.connect();
        } else {
            Toast.makeText(this, "Location Already Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void stateDataLoading() {
        mStateSpinnerData = new ArrayList<>();
        mStateSpinnerData.add("Select State");
        mStateSpinnerData.add("Hariyana");
        mStateSpinnerData.add("Kerala");
        mStateSpinnerData.add("Karnataka");
        mStateSpinnerData.add("Tamil Nadu");
        setStateListData(mStateSpinnerData, mStateSpinner);
    }

    public void setStateListData(ArrayList<String> mStateSpinnerData, AppCompatSpinner mStateSpinner) {
        MyArrayAdapter arrayAdapterTitle = new MyArrayAdapter(this, R.layout.layout_drop_title_black, mStateSpinnerData, -1);
        arrayAdapterTitle.setDropDownViewResource(R.layout.layout_drop_list);
        mStateSpinner.setAdapter(arrayAdapterTitle);
        mStateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentSate = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.current_location) {
            setUpGClient();
        }
        if (v.getId() == R.id.venue_back_btn) {
            finish();
        }
        if (v.getId() == R.id.submit_btn) {
            if (mInputName.getText().toString().length() == 0 && mEmailId.getText().toString().length() == 0 && mPhone.getText().toString().length() == 0
                    && mContactPerson.toString().length() == 0 && mWebsiteAddress.toString().length() == 0 && mMobile.getText().toString().length() == 0) {
                Toast.makeText(this, "All Fields Mandatory", Toast.LENGTH_SHORT).show();
            } else if (mInputName.getText().toString().length() == 0) {
                if (!validateName()) ;
            } else if (currentSate.equals("Select State") && (latitude == 0d || longitude == 0d)) {
                Toast.makeText(this, "Select Your Location", Toast.LENGTH_SHORT).show();
            } else if (mEmailId.getText().toString().length() == 0) {
                if (!validateEmail()) ;
            } else if (!validEmail(mEmailId.getText().toString())) {
                if (!validateEmailPattern()) ;
            } else if (mPhone.getText().toString().length() == 0) {
                if (!validatePhone()) ;
            } else if (mContactPerson.getText().toString().length() == 0) {
                if (!validateContactPerson()) ;
            } else if (mWebsiteAddress.getText().toString().length() == 0) {
                if (!validateWebsite()) ;
            } else if (mMobile.getText().toString().length() == 0) {
                if (!validateMobile()) ;
            } else {
                Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show();
            }

        }
    }


    private boolean validateName() {
        if (mInputName.getText().toString().length() == 0) {
            mInputTextNameLayout.setError("Enter " + validationName + " Name");
            requestFocus(mInputName);
            return false;
        } else {
            mInputTextNameLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        if (mEmailId.getText().toString().length() == 0) {
            mInputTextEmailLayout.setError("Enter Your Email Address");
            requestFocus(mEmailId);
            return false;
        } else {
            mInputTextEmailLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmailPattern() {
        if (!validEmail(mEmailId.getText().toString())) {
            mInputTextEmailLayout.setError("Enter a Valid Email Address");
            requestFocus(mEmailId);
            return false;
        } else {
            mInputTextEmailLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validateContactPerson() {
        if (mContactPerson.getText().toString().trim().length() == 0) {
            mInputTextContactPerson.setError("Enter a Person Name for Contact");
            requestFocus(mContactPerson);
            return false;
        } else {
            mInputTextContactPerson.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateWebsite() {
        if (mWebsiteAddress.getText().toString().trim().length() == 0) {
            mInputTextWebsite.setError("Enter a Website");
            requestFocus(mWebsiteAddress);
            return false;
        } else {
            mInputTextWebsite.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMobile() {
        if (mMobile.getText().toString().length() == 0) {
            mInputTextMobile.setError("Enter a Mobile Number");
            requestFocus(mMobile);
            return false;
        } else {
            mInputTextMobile.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        if (mPhone.getText().toString().length() == 0) {
            mInputTextPhone.setError("Enter a Phone Number");
            requestFocus(mPhone);
            return false;
        } else {
            mInputTextPhone.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_email:
                    validateEmail();
                    if (validateEmail()) {
                        validateEmailPattern();
                    }
                    break;
                case R.id.input_contact_person:
                    validateContactPerson();
                    break;
                case R.id.input_website_address:
                    validateWebsite();
                    break;
                case R.id.input_mobile:
                    validateMobile();
                    break;
                case R.id.input_phone:
                    validatePhone();
            }
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkPermissions();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "ConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "ConnectionFailed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        mylocation = location;
        if (mylocation != null) {
            latitude = mylocation.getLatitude();
            longitude = mylocation.getLongitude();
            Toast.makeText(this, "latitude:" + latitude + "   " + "longitude:" + longitude, Toast.LENGTH_SHORT).show();
            //Or Do whatever you want with your location
        }
    }


    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(VenueProviderActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {
            getMyLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(VenueProviderActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            getMyLocation();
        }
    }

    private void getMyLocation() {
        if (googleApiClient != null) {
            if (googleApiClient.isConnected()) {
                int permissionLocation = ContextCompat.checkSelfPermission(VenueProviderActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
                if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                    mylocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    LocationRequest locationRequest = new LocationRequest();
                    locationRequest.setInterval(20000);
                    locationRequest.setFastestInterval(20000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                            .addLocationRequest(locationRequest);
                    builder.setAlwaysShow(true);
                    LocationServices.FusedLocationApi
                            .requestLocationUpdates(googleApiClient, locationRequest, this);// for updating the location from the google api client
                    PendingResult result =
                            LocationServices.SettingsApi
                                    .checkLocationSettings(googleApiClient, builder.build());// if the permission for accesing location is denied on any other way
                    result.setResultCallback(new ResultCallback() {
                        @Override
                        public void onResult(Result result) {
                            final Status status = result.getStatus();//if denied as per the network issue or GPS error
                            switch (status.getStatusCode()) {
                                case LocationSettingsStatusCodes.SUCCESS:
                                    // All location settings are satisfied.
                                    // You can initialize location requests here.
                                    int permissionLocation = ContextCompat
                                            .checkSelfPermission(VenueProviderActivity.this,
                                                    Manifest.permission.ACCESS_FINE_LOCATION);
                                    if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                                        mylocation = LocationServices.FusedLocationApi
                                                .getLastLocation(googleApiClient);// if there any delay for accesing the location it shows the last location known from the client
                                    }
                                    break;
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                    // Location settings are not satisfied.
                                    // But could be fixed by showing the user a dialog.
                                    try {
                                        // Show the dialog by calling startResolutionForResult(),
                                        // and check the result in onActivityResult().
                                        // Ask to turn on GPS automatically
                                        status.startResolutionForResult(VenueProviderActivity.this,
                                                REQUEST_CHECK_SETTINGS_GPS);
                                    } catch (IntentSender.SendIntentException e) {
                                        // Ignore the error.
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    // Location settings are not satisfied. However, we have no way to fix the
                                    // settings so we won't show the dialog.
                                    //finish();
                                    break;
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS_GPS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        getMyLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        finish();
                        break;
                }
                break;
        }
    }

}
