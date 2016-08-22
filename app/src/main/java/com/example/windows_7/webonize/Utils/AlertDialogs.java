package com.example.windows_7.webonize.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AlertDialogs {


    public static void showAlert(Activity context,String msg){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.show();
    }


}
