package com.aone.onlinefix.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aone.onlinefix.App;

/**
 * Created by abuzeid-ibrahim on 10/28/17.
 */

public class ui {
    public  static  void show(Context context,int text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();

    }
    public  static  void show(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }



    private static AlertDialog dialog;


    public static void load(Context context) {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //builder.setMessage("Fix Phone")
        ProgressBar progressBar = new ProgressBar(context);
        builder.setView(progressBar);
        dialog = builder.create();
        dialog.show();
    }

    public static void dismissLoading() {
        if (dialog != null)
            if (dialog.isShowing()) {
                dialog.hide();
            }
    }


    public static Context getContext() {
        return App.getContext();
    }
}
