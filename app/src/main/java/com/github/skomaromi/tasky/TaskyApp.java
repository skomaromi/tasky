package com.github.skomaromi.tasky;

import android.app.Application;

public class TaskyApp extends Application {
    private static TaskyApp sInstance;

    public static TaskyApp getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
