package com.aone.onlinefix;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by abuzeid-ibrahim on 10/20/17.
 */

public class App extends Application {



private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
context = this;
    }
}
