package com.shubham.location;

import static android.content.ContentValues.TAG;
import static android.net.rtp.AudioCodec.GSM;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.net.Uri;
import android.net.rtp.AudioCodec;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.CellIdentity;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Locale;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import java.io.IOException;

//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;


public class MainActivity extends AppCompatActivity implements LocationListener {


    LocationManager locationManager;
    TextView tvPhoneType, tvCellLocation;
    TextView tvCity, tvState, tvCountry, tvPin, tvLocality;

    boolean gps_enabled = false;
    boolean network_enabled = false;

    //OpenCellID openCellID;

    Func func;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        func = new Func(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }

/*
        //retrieve a reference to an instance of TelephonyManager
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        */

        //TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        //GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
        //GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();
        //CdmaCellLocation gsmcellLocation = (CdmaCellLocation) telephonyManager.getCellLocation();

        //CellLocation cellInfoList = telephonyManager.getCellLocation();
        //CellIdentity identityGsm = GsmCellLocation.;
        //final CellIdentityGsm identityGsm = ((CellInfoGsm) info)
                //.getCellIdentity();









        //.getCellLocation()
        //GsmCellLocation cellLocation = (GsmCellLocation)telephonyManager.getCellLocation();


        tvPhoneType = findViewById(R.id.tvPhoneType);
        tvCellLocation = findViewById(R.id.tvCellLocation);
        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvCountry = findViewById(R.id.tvCountry);
        tvPin = findViewById(R.id.tvPin);
        tvLocality = findViewById(R.id.tvLocality);

        //locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //locationManager.setPriority(LocationRequest.);
        //locationEnabled();
        //getLocation();

        //cellInfoList.g


        //List<CellInfo> neighbors = telephonyManager.getAllCellInfo();

        //tvPhoneType.setText(getPhoneType(telephonyManager));
        //tvCellLocation.setText((CharSequence) cellLocation);

        // List<NeighboringCellInfo> cellInfo = telephonyManager.getN;


        //int cid = cellLocation.getCid();
        //int lac = cellLocation.getLac();
        //String networkOperator = telephonyManager.getNetworkOperator();
        //String mcc = networkOperator.substring(0, 3);
        //String mnc = networkOperator.substring(3);

        //openCellID = new OpenCellID();

        //openCellID.setMcc(mcc);
        //openCellID.setMnc(mnc);
        //openCellID.setCallID(cid);
        //openCellID.setCallLac(lac);

        func.loadCellInfoRoute();


        //Log.d("LL","44");
        //uu("http://www.google.com/search?q=dsdgsdaswf34235235");
        //uu("http://www.google.com/");
        //uu("");
    }


    //public void uu (String URL) {
   //     locationGoogleSearch(URL);
    //}
    //public void locationGoogleSearch(String URL){
    //    try {
            //Uri uri= Uri.parse(URL);
            //String id = uri.getQueryParameter(".Q8LRLc");
        //Log.d("FAB", id);


            //List<NameValuePair> parse = URLEncodedUtils.parse(URI.create(URL), "UTF-8");
            //for (NameValuePair pair : parse) {
            //    System.out.println("!!!!!!!!!! " + pair.getName() + " = " + pair.getValue());
            //}

      //      Document doc = Jsoup.connect(URL)
       //             .userAgent("Mozilla/5.0")
       //             .timeout(30000)
       //             .ignoreContentType(true)
       //             .ignoreHttpErrors(true).get();

            //Document document = Jsoup.parse(htmlFile, "UTF-8");

            //System.out.println( document.text() );
            //Document response = Jsoup.connect("https://en.wikipedia.org/").method(Connection.Method.GET).ignoreContentType(true).ignoreHttpErrors(true).get();
            //log(doc.title());


            //Connection.Response doc = Jsoup.connect(URL)
            //        .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
            //        .timeout(10000)
           //         .ignoreContentType(true)
            //        .execute();
            //String country = doc.select(".Q8LRLc").text();
            //String city = doc.select(".dfB0uf").text();

            //Log.d("FAB", country);
            //Log.d("FAB", city);
       // } catch (Exception e) {
      //      throw new RuntimeException(e);
      //  }
   // }



    private void locationEnabled() {
        //LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        //try {
        //    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            Log.d("FAB", "network_enabled " + network_enabled);
        } catch (Exception e) {
            Log.d("FAB", "network_enabled " + e);
            e.printStackTrace();
        }
/*
        if (!gps_enabled && !network_enabled) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Enable GPS Service")
                    .setMessage("We need your GPS location to show Near Places around you.")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new
                            DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                    .setNegativeButton("Cancel", null)
                    .show();
        }

 */


    }

    void getLocation() {
        try {
            Log.d("FAB", "getLocation");
           // locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();

            Log.d("FAB", "error getLocation");
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Log.d("FAB", "onLocationChanged");
            Log.d("FAB", String.valueOf(location.getLatitude()));
            Log.d("FAB", String.valueOf(location.getLongitude()));
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            tvCity.setText(addresses.get(0).getLocality());
            tvState.setText(addresses.get(0).getAdminArea());
            tvCountry.setText(addresses.get(0).getCountryName());
            tvPin.setText(addresses.get(0).getPostalCode());
            tvLocality.setText(addresses.get(0).getAddressLine(0));

        } catch (Exception e) {

            Log.d("FAB", "Exception");
            Log.d("FAB", String.valueOf(e));
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("FAB", "onStatusChanged");
        Log.d("FAB", "provider " + provider);
        Log.d("FAB", "status " + status);
        Log.d("FAB", "extras " + extras);

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("FAB", "onProviderEnabled");
        Log.d("FAB", "provider " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("FAB", "onProviderDisabled");
        Log.d("FAB", "provider " + provider);

    }
}
