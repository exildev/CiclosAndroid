package co.com.script.ciclos;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.places.Places;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class PlanillaActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 3;
    private static final int REQUEST_LOCATION_SETTINGS = 12;

    private int piscina;
    private JSONArray formJSON;
    private int formulario;
    private int registro;
    private double lat;
    private double lng;

    private GoogleApiClient mGoogleClient;
    private LocationRequest mLocationRequest;
    private Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planilla);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hideLoading();

        piscina = getIntent().getIntExtra("piscina", -1);

        mGoogleClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        mGoogleClient.connect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.planilla, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_send) {
            send();
        }
        return super.onOptionsItemSelected(item);
    }

    public void send() {
        showLoading();
        final LinearLayout form_container = (LinearLayout) findViewById(R.id.form_container);
        final CheckBox espera = (CheckBox) findViewById(R.id.espera);
        String serviceUrl = getString(R.string.send_form, registro);

        String url = getString(R.string.url, serviceUrl);
        final StringRequest loginRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("response", response);
                        Intent data = new Intent();
                        data.putExtra("response", response);
                        data.putExtra("status", 200);
                        setResult(RESULT_OK, data);
                        Toast.makeText(PlanillaActivity.this, "Registro guardado con exito", Toast.LENGTH_LONG).show();
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                            hideLoading();
                            final CardView container = (CardView) findViewById(R.id.error_container);
                            container.setVisibility(View.VISIBLE);
                            VolleySingleton.manageError(PlanillaActivity.this, error, container, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    container.setVisibility(View.GONE);
                                    crearRegistro();
                                    Log.i("rety", "rety");
                                }
                            });
                        } else {
                            String err = new String(error.networkResponse.data);
                            Intent data = new Intent();
                            data.putExtra("response", err);
                            data.putExtra("status", error.networkResponse.statusCode);
                            setResult(RESULT_OK, data);
                            for (String r : err.split("\n")) {
                                Log.e("solucion", r);
                            }
                            finish();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                for (int i = 0; i < formJSON.length(); i++) {
                    try {
                        JSONObject fieldJson = formJSON.getJSONObject(i);
                        String type = fieldJson.getString("tipo__nombre");
                        if (type.equals("Checkbox")) {
                            FrameLayout field_layout = (FrameLayout) form_container.getChildAt(i + 1);
                            CheckBox field = (CheckBox) field_layout.getChildAt(0);
                            String name = field.getText().toString();
                            boolean value = field.isChecked();
                            params.put(name, String.valueOf(value));
                        } else {
                            TextInputLayout field_layout = (TextInputLayout) form_container.getChildAt(i + 1);
                            String name = field_layout.getHint().toString();
                            String value = field_layout.getEditText().getText().toString();
                            params.put(name, value);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (espera.isChecked()) {
                    params.put("espera", "on");
                }
                params.put("latitud", String.valueOf(lat));
                params.put("longitud", String.valueOf(lng));
                return params;
            }
        };
        loginRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleySingleton.getInstance(this).addToRequestQueue(loginRequest);
    }

    void crearRegistro() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            validPermissions();
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleClient);
        if (mLastLocation != null) {
            myLocation = mLastLocation;
        }
        try {
            final int operario = HomeActivity.USER.getInt("id");
            showLoading();
            String serviceUrl = getString(R.string.crear_registro);
            String url = getString(R.string.url, serviceUrl);
            StringRequest loginRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i("REGISTRO", response.toString());
                                hideLoading();
                                JSONObject r = new JSONObject(response);
                                registro = r.getInt("id");
                                lat = r.getDouble("latitud");
                                lng = r.getDouble("longitud");
                                renderForm();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Log.i("error", new String(error.networkResponse.data));
                            hideLoading();
                            final CardView container = (CardView) findViewById(R.id.error_container);
                            container.setVisibility(View.VISIBLE);
                            VolleySingleton.manageError(PlanillaActivity.this, error, container, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    container.setVisibility(View.GONE);
                                    crearRegistro();
                                    Log.i("rety", "rety");
                                }
                            });
                            Log.e("Activities", error.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("formulario", String.valueOf(formulario));
                    params.put("operario", String.valueOf(operario));
                    params.put("latitud", myLocation.getLatitude() + "");
                    params.put("longitud", myLocation.getLongitude() + "");
                    return params;
                }
            };
            loginRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(this).addToRequestQueue(loginRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void renderForm() {
        showLoading();
        String serviceUrl = getString(R.string.show_form, formulario);
        String url = getString(R.string.url, serviceUrl);
        JsonObjectRequest reportesRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    hideLoading();
                    Log.i("Form", response.toString());
                    JSONArray object_list = response.getJSONArray("object_list");
                    formJSON = object_list;
                    for (int i = 0; i < object_list.length(); i++) {
                        appendField(object_list.getJSONObject(i));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideLoading();
                final CardView container = (CardView) findViewById(R.id.error_container);
                container.setVisibility(View.VISIBLE);
                VolleySingleton.manageError(PlanillaActivity.this, error, container, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        container.setVisibility(View.GONE);
                        renderForm();
                        Log.i("rety", "rety");
                    }
                });
                Log.e("Activities", error.toString());
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(reportesRequest);
    }

    void appendField(JSONObject field) throws JSONException {
        LinearLayout form_container = (LinearLayout) findViewById(R.id.form_container);
        LayoutInflater inflater = LayoutInflater.from(this);
        String hint = field.getString("nombre");
        String type = field.getString("tipo__nombre");
        switch (type) {
            case "Text":
                appendTextField(hint, inflater, form_container);
                break;
            case "Checkbox":
                appendCheckboxField(hint, inflater, form_container);
                break;
            case "Fecha":
                appendDateField(hint, inflater, form_container);
                break;
            case "Hora":
                appendHourField(hint, inflater, form_container);
                break;
            case "Decimal":
                appendDecimalField(hint, inflater, form_container);
                break;
            case "Entero":
                appendIntegerField(hint, inflater, form_container);
                break;
            case "TextArea":
                appendTextAreaField(hint, inflater, form_container);
                break;
            case "Fecha y Hora":
                appendDateTimeField(hint, inflater, form_container);
                break;
        }
    }

    void appendTextField(String hint, LayoutInflater inflater, ViewGroup container) {
        TextInputLayout fieldView = (TextInputLayout) inflater.inflate(R.layout.text_field, container, false);
        fieldView.setHint(hint);
        container.addView(fieldView);
    }

    void appendTextAreaField(String hint, LayoutInflater inflater, ViewGroup container) {
        TextInputLayout fieldView = (TextInputLayout) inflater.inflate(R.layout.textarea_field, container, false);
        fieldView.setHint(hint);
        container.addView(fieldView);
    }

    void appendDecimalField(String hint, LayoutInflater inflater, ViewGroup container) {
        TextInputLayout fieldView = (TextInputLayout) inflater.inflate(R.layout.decimal_field, container, false);
        fieldView.setHint(hint);
        container.addView(fieldView);
    }

    void appendIntegerField(String hint, LayoutInflater inflater, ViewGroup container) {
        TextInputLayout fieldView = (TextInputLayout) inflater.inflate(R.layout.integer_field, container, false);
        fieldView.setHint(hint);
        container.addView(fieldView);
    }

    void appendCheckboxField(String hint, LayoutInflater inflater, ViewGroup container) {
        FrameLayout fieldView = (FrameLayout) inflater.inflate(R.layout.checkbox_field, container, false);
        CheckBox checkBox = (CheckBox) fieldView.getChildAt(0);
        checkBox.setText(hint);
        container.addView(fieldView);
    }

    void appendDateField(String hint, LayoutInflater inflater, ViewGroup container) {
        TextInputLayout fieldView = (TextInputLayout) inflater.inflate(R.layout.time_field, container, false);
        fieldView.setHint(hint);
        final EditText field = fieldView.getEditText();
        assert field != null;
        field.setInputType(InputType.TYPE_NULL);

        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
                String currDateStr = field.getText().toString();
                Calendar currDate = Calendar.getInstance();
                if (!currDateStr.equals("")) {
                    try {
                        Date d = dateFormat.parse(currDateStr);
                        currDate.setTime(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar c = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                                field.setText(dateFormat.format(c.getTime()));
                            }
                        },
                        currDate.get(Calendar.YEAR),
                        currDate.get(Calendar.MONTH),
                        currDate.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        container.addView(fieldView);
    }

    void appendHourField(String hint, LayoutInflater inflater, ViewGroup container) {
        TextInputLayout fieldView = (TextInputLayout) inflater.inflate(R.layout.time_field, container, false);
        fieldView.setHint(hint);
        final EditText field = fieldView.getEditText();
        assert field != null;
        field.setInputType(InputType.TYPE_NULL);

        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.US);
                String currDateStr = field.getText().toString();
                Calendar currDate = Calendar.getInstance();
                if (!currDateStr.equals("")) {
                    try {
                        Date d = dateFormat.parse(currDateStr);
                        currDate.setTime(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                TimePickerDialog dpd = TimePickerDialog.newInstance(
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                                field.setText(dateFormat.format(c.getTime()));
                            }
                        },
                        currDate.get(Calendar.HOUR_OF_DAY),
                        currDate.get(Calendar.MINUTE),
                        true
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        container.addView(fieldView);
    }

    void appendDateTimeField(String hint, LayoutInflater inflater, ViewGroup container) {
        TextInputLayout fieldView = (TextInputLayout) inflater.inflate(R.layout.time_field, container, false);
        fieldView.setHint(hint);

        final EditText field = fieldView.getEditText();
        assert field != null;
        field.setInputType(InputType.TYPE_NULL);

        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US);
                String currDateStr = field.getText().toString();
                final Calendar currDate = Calendar.getInstance();
                if (!currDateStr.equals("")) {
                    try {
                        Date d = dateFormat.parse(currDateStr);
                        currDate.setTime(d);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                currDate.set(year, monthOfYear, dayOfMonth);
                                TimePickerDialog dpd = TimePickerDialog.newInstance(
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                                currDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                                currDate.set(Calendar.MINUTE, minute);
                                                field.setText(dateFormat.format(currDate.getTime()));
                                            }
                                        },
                                        currDate.get(Calendar.HOUR_OF_DAY),
                                        currDate.get(Calendar.MINUTE),
                                        true
                                );
                                dpd.show(getFragmentManager(), "Datepickerdialog");
                            }
                        },
                        currDate.get(Calendar.YEAR),
                        currDate.get(Calendar.MONTH),
                        currDate.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
        container.addView(fieldView);
    }

    private void showLoading() {
        View modal = findViewById(R.id.modal);
        modal.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        View modal = findViewById(R.id.modal);
        modal.setVisibility(View.GONE);
    }

    private void validPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            } else if (checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
            } else {
                createLocationRequest();
            }
        } else {
            createLocationRequest();
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleClient,
                        builder.build());


        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.
                        Log.i("settings", "si tal");
                        startLocationUpdates();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(PlanillaActivity.this, REQUEST_LOCATION_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        Log.i("settings", "no tal");
                        break;
                }
            }
        });
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            validPermissions();
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleClient, mLocationRequest, this);
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleClient, this);
    }

    @Override
    public void onDestroy() {
        if (mGoogleClient.isConnected()) {
            stopLocationUpdates();
        }
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION || requestCode == PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
                validPermissions();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.gps_permissions_message)
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                validPermissions();
                            }
                        })
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        createLocationRequest();
        if (getIntent().hasExtra("formulario") && !getIntent().hasExtra("registro")) {
            try {
                JSONObject formJson = new JSONObject(getIntent().getStringExtra("formulario"));
                formulario = formJson.getInt("id");
                crearRegistro();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            formulario = -1;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        myLocation = location;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOCATION_SETTINGS) {
            Log.i("GPS", "" + (resultCode == Activity.RESULT_OK));
            if (resultCode == Activity.RESULT_OK) {
                startLocationUpdates();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.activate_gps_message)
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                createLocationRequest();
                            }
                        })
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
