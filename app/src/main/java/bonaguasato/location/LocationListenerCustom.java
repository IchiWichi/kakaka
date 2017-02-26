package bonaguasato.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Daniel on 1/23/2017.
 */

public class LocationListenerCustom implements LocationListener {
    @Override
    public void onLocationChanged(Location location) {
        MainActivity.location = location;
        Log.d("TEST", "onLocationChanged: "+location.getLatitude()+","+location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
