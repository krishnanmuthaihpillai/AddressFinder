package com.example.addressfinder2;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	double LATITUDE = 37.42233;
	double LONGITUDE = -122.083;
	 GPSTracker gps; 
	  String lan,lon;
	  Handler handler;
	  Runnable runnable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  findaddress();

		
		
	       TextView myLatitude = (TextView)findViewById(R.id.mylatitude);
	       TextView myLongitude = (TextView)findViewById(R.id.mylongitude);
	       TextView myAddress = (TextView)findViewById(R.id.myaddress);
	      
	       myLatitude.setText("Latitude: " + String.valueOf(LATITUDE));
	       myLongitude.setText("Longitude: " + String.valueOf(LONGITUDE));
	      
	       Geocoder geocoder = new Geocoder(this, Locale.ENGLISH);

	       try {
	  List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
	 
	  if(addresses != null) {
	   Address returnedAddress = addresses.get(0);
	   StringBuilder strReturnedAddress = new StringBuilder("Address:\n");
	   for(int i=0; i<returnedAddress.getMaxAddressLineIndex(); i++) {
	    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
	   }
	   myAddress.setText(strReturnedAddress.toString());
	  }
	  else{
	   myAddress.setText("No Address returned!");
	  }
	 } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	  myAddress.setText("Canont get Address!");
	 }

	   }
	
	
	private void findaddress() {
		// TODO Auto-generated method stub
		 runnable = new Runnable() {

			  public void run() {
					// TODO Auto-generated method stub
				     gps = new GPSTracker(MainActivity.this); 
				        if(gps.canGetLocation()){                  
				            double latitude = gps.getLatitude();
				            double longitude = gps.getLongitude();  
				            latitude=LATITUDE;
				            longitude=LONGITUDE;
				             lan = String.valueOf(latitude);
				             lon =String.valueOf(longitude);
				     
				            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + lan + "\nLong: " + lon, Toast.LENGTH_LONG).show();   
				        }else{
				            gps.showSettingsAlert();
				        } 
				}
		  };
		  handler = new Handler();
		  handler.postDelayed(runnable,500);
	}
	
	
	
	
	}