package com.example.windows_7.webonize.Utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {


    public static void showToastShortMessage (Activity activity,String msg) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        float density = displayMetrics.density;
        Toast toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, (int)(83 * density));
        toast.show();
    }
}
