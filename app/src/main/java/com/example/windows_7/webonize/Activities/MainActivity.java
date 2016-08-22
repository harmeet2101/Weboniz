package com.example.windows_7.webonize.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.windows_7.webonize.Adapters.AutoCompleteListAdapter;
import com.example.windows_7.webonize.Fragments.PlaceListFragment;
import com.example.windows_7.webonize.Model.Place;
import com.example.windows_7.webonize.Model.PlaceList;
import com.example.windows_7.webonize.Networking.Connectivity;
import com.example.windows_7.webonize.Networking.Constants;
import com.example.windows_7.webonize.Networking.CurrentLocation;
import com.example.windows_7.webonize.Networking.VolleyCustomRequest;
import com.example.windows_7.webonize.Networking.VolleyRequestBuilder;
import com.example.windows_7.webonize.R;
import com.example.windows_7.webonize.Utils.KeyboardUtils;
import com.example.windows_7.webonize.Utils.ToastUtils;
import com.example.windows_7.webonize.Utils.UrlUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements PlaceListFragment.OnFragmentInteractionListener,TextWatcher{


    AutoCompleteListAdapter listAdapter;
    AutoCompleteTextView searchText;
    ArrayList<String> suggestions=new ArrayList<>();
    Button searchButton;
    CurrentLocation mCurrentLocation;
    double lat,lng;
    RequestQueue requestQueue;
    VolleyRequestBuilder volleyRequestBuilder;
    PlaceListFragment placeListFragment;
    Gson gson;
    PlaceList placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText=(AutoCompleteTextView)findViewById(R.id.searchbar);
        searchButton=(Button)findViewById(R.id.searchButton);
        placeListFragment=(PlaceListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyboardUtils.tryToHideKeyboard(MainActivity.this);
                makeRestPlacesApiCall();
            }
        });
        searchText.addTextChangedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("GPS settings");
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    @Override
    public List<Place> getPlaceList() {
        if(placeList!=null)
        return placeList.getResults();
        else
            return null;
    }


    private void notifyPlaceListDataReceived(){
        if (placeListFragment != null) {
            placeListFragment.showPlacesList(lat,lng);
        }
    }

    public void showFetchingDataProgressDialog(boolean flag){

        if(flag) {
            progress = new ProgressDialog(MainActivity.this);
            progress.setMessage("Loading...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
        }else if(progress!=null && progress.isShowing()){
            progress.dismiss();
        }
    }

    private ProgressDialog progress;


    public void makeRestPlacesApiCall(){
        if(!Connectivity.isNetworkAvailable(getApplicationContext())){
            Toast.makeText(getApplicationContext(),"Not connected to internet",Toast.LENGTH_SHORT).show();
        }else
        {
            mCurrentLocation=new CurrentLocation(getApplicationContext());

            if(searchText.getText().toString().equalsIgnoreCase("")){
                Toast.makeText(getApplicationContext(),"please enter place name",Toast.LENGTH_SHORT).show();
                return;
            }
            if(mCurrentLocation.canGetLocation()){
                lat=mCurrentLocation.getLatitude();
                lng=mCurrentLocation.getLongitude();
                volleyRequestBuilder= VolleyRequestBuilder.getVolleyInstance(getApplicationContext());
                requestQueue=volleyRequestBuilder.getRequestQueue();
                showFetchingDataProgressDialog(true);
                Map<String,String> params=new HashMap();

                params.put("location",lat+","+lng);
                params.put("radius","500");
                params.put("sensor","true");
                params.put("key",Constants.API_KEY);
                params.put("name", UrlUtils.encode(searchText.getText().toString(), "UTF-8"));
                String customUrl= Constants.place_serach_url;
                VolleyCustomRequest volleyCustomRequest=new VolleyCustomRequest(Request.Method.GET, customUrl,
                        params, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());
                        gson=new Gson();
                        placeList=gson.fromJson(response.toString(),PlaceList.class);
                        if(placeList!=null) {
                            showStatusOnToast(placeList.getStatus());
                        }if(!suggestions.contains(searchText.getText().toString()))
                        suggestions.add(searchText.getText().toString());
                       //suggestions_final=toArrayList(suggestions);
                        listAdapter=new AutoCompleteListAdapter(getApplicationContext(),suggestions);
                        searchText.setAdapter(listAdapter);
                        searchText.setThreshold(1);;
                        notifyPlaceListDataReceived();
                        showFetchingDataProgressDialog(false);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error: ",error.toString());
                    }
                });
                volleyRequestBuilder.addToRequestQueue(volleyCustomRequest);

            }else
                showSettingsAlert();
        }
    }



    public void onPause(){
        super.onPause();
    }

    public void onStop(){
        super.onStop();
    }

    public void onDestroy(){
        super.onDestroy();
    }



        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(searchText.getText().toString().equalsIgnoreCase("")){
                if(placeListFragment!=null &&placeList!=null){
                    placeList.setResults(null);
                    notifyPlaceListDataReceived();
                }
            }

        }


    public void showStatusOnToast(String status){
        switch (status){

            case "OK":

                break;
            case "ZERO_RESULTS":
                ToastUtils.showToastShortMessage(MainActivity.this,"search was successful but returned no results");
                break;
            case "OVER_QUERY_LIMIT":
                ToastUtils.showToastShortMessage(MainActivity.this,"You have exceeded your daily request quota for this API.");
                break;
            case "REQUEST_DENIED":
                ToastUtils.showToastShortMessage(MainActivity.this,"your request was denied, generally because of lack of an invalid key parameter.");
                break;
            case "INVALID_REQUEST":
                ToastUtils.showToastShortMessage(MainActivity.this," required query parameter (location or radius) is missing");
                break;
            default:
                ToastUtils.showToastShortMessage(MainActivity.this,"Unknown Error");
        }
    }

}
