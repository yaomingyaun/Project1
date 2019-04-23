package com.bw.ymy.project.App;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class App  extends Application {
    private  static Context mcontext;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        mcontext=getApplicationContext();
    }
    public  static Context getApplication()
    {
        return  mcontext;
    }
    public static void saveBitmap(Bitmap bitmap, String path, int quality) throws IOException {
        String dir = path.substring(0, path.lastIndexOf("/"));
        File dirFile = new File(dir);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            try {
                if (!dirFile.mkdirs()) {
                    return;
                }
            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            }

        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, quality, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("TAG", e.getMessage());
        }
    }
}
