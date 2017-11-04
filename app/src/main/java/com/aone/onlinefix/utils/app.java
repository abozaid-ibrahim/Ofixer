package com.aone.onlinefix.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.aone.onlinefix.model.Store;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by abuzeid-ibrahim on 10/27/17.
 */

public class app {

    public static String timeNow(){

        long millisecond = System.currentTimeMillis();
        return DateFormat.getDateTimeInstance().format(new Date());
//Oct 29, 2017 9:13:40 AM

        //Log.d("vip",currentDateTimeString);
       // return new Date(System.currentTimeMillis()).toString();
    }




    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy h:mm a";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }




    public static String md5(String string){


            try {
                // Create MD5 Hash
                MessageDigest digest = MessageDigest
                        .getInstance("MD5");
                digest.update(string.getBytes());
                byte messageDigest[] = digest.digest();

                // Create Hex String
                StringBuffer hexString = new StringBuffer();
                for (int i = 0; i < messageDigest.length; i++) {
                    String h = Integer.toHexString(0xFF & messageDigest[i]);
                    while (h.length() < 2)
                        h = "0" + h;
                    hexString.append(h);
                }
                return hexString.toString();

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }

    public static String firToken() {
        return "asfdasdf";//FirebaseInstanceId.getInstance().getToken();
    }


    public static void makeCall(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.setData(Uri(""));
        ui.getContext().startActivity(intent);

    }
}
