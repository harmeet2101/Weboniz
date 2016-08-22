package com.example.windows_7.webonize.Utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class FileUtils {


    public static boolean saveImageToSDCard(Activity context,Bitmap b) {
        if (b != null) {
            try {
                File sdCardFile = Environment.getExternalStorageDirectory();
                File[] dirs= ContextCompat.getExternalFilesDirs(context, null);


                if (sdCardFile.canWrite() == true) {
                    File viewerFile = new File("/storage/sdcard0/webonize/saved_images");
                    viewerFile.mkdirs();
                    Random generator = new Random();
                    int n = 10000;
                    n = generator.nextInt(n);
                    String fname = "Image-"+ n +".png";
                    File imageFile = new File(viewerFile,fname);
                    FileOutputStream fileStream = new FileOutputStream(imageFile);
                    b.compress(Bitmap.CompressFormat.PNG, 100, fileStream);
                    fileStream.close();
                    ToastUtils.showToastShortMessage(context, "Image successfully saved at " + viewerFile.getCanonicalPath().toString());
                } else {
                    Log.e("save image error_else", "IOUtility - Cannot write to SD Card");
                }
                return true;
            } catch (Exception e) {
                Log.e("save image error", "IOUtility - Error - " + e);
                AlertDialogs.showAlert(context, "sdcard not available");
                e.printStackTrace();
            }
        }
        return false;
    }
}
