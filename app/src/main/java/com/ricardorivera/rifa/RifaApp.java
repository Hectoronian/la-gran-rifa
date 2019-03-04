package com.ricardorivera.rifa;

import android.app.Application;
import android.os.SystemClock;

public class RifaApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1000);
    }
}
