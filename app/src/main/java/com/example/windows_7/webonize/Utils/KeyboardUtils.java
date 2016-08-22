package com.example.windows_7.webonize.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by windows-7 on 8/20/2016.
 */
public class KeyboardUtils {

    public static void tryToHideKeyboard(Activity activity) {
            if (activity != null) {
                final InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                View focusedView = activity.getCurrentFocus();
                IBinder windowToken = null;
                if (focusedView != null) {
                    windowToken = focusedView.getWindowToken();
                }
                if (imm != null && windowToken != null) {
                    imm.hideSoftInputFromWindow(windowToken, 0);
                }
            }
        }

}
