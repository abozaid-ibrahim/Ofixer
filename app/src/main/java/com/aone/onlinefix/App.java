package com.aone.onlinefix;

import android.app.Application;
import android.content.Context;

/**
 * Created by abuzeid-ibrahim on 10/20/17.
 */

public class App extends Application {



private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
context = this;
    }

    public static Context getContext() {
        return context;
    }
}
