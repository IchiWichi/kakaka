package bonaguasato;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import bonaguasato.location.LocationListenerCustom;
import bonaguasato.location.R;
import bonaguasato.location.User_profiles;
import bonaguasato.location.Web_behavior;
import bonaguasato.location.Web_location;

public class MainActivity extends AppCompatActivity {
    private LocationListener    listener;
    private LocationManager     locationManager;
    public static Location      location;
   // private DatabaseHelper      helper;
    ImageButton myImagebutton, myImagebutton2, myImagebutton3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // helper = new DatabaseHelper(this);
       // helper.insertUser("philip","pbonagua@openaccesmarketing.com");
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListenerCustom();
        statusCheck();
        requestLocation();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });

        myImagebutton = (ImageButton) findViewById(R.id.imgbtnUser);
        myImagebutton2 = (ImageButton) findViewById(R.id.imgbtnlocation);
        myImagebutton3 = (ImageButton) findViewById(R.id.imgbtnscan);


        myImagebutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent myIntent = new Intent(MainActivity.this, User_profiles.class);
                startActivity(myIntent);


            }
        } );myImagebutton2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, Web_location.class);
                startActivity(myIntent);
            }}    );

        myImagebutton3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this, Web_behavior.class);
                startActivity(myIntent);
            }}    );


    }

    private void makeRequest() {
        Double latitude = 0.0;
        Double longitude = 0.0;
        try{
            latitude = MainActivity.location.getLatitude();
            longitude = MainActivity.location.getLongitude();
        }catch (Exception e){
            latitude = 0.0;
            longitude = 0.0;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.43.71/thesis/savelocation.php?lat="+latitude+"&lng="+longitude,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
    }

}
