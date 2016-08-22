package com.example.windows_7.webonize.Networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by windows-7 on 8/20/2016.
 */
public class Connectivity {


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] networkInfo = cm.getAllNetworkInfo();

        for (int i=0;i<networkInfo.length;i++){

            if (networkInfo[i].getState()==NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }
}
