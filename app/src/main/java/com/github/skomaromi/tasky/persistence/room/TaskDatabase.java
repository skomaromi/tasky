package com.github.skomaromi.tasky.persistence.room;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.github.skomaromi.tasky.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase sInstance;
    private static final String DATABASE_NAME = "tasks.db";

    public static TaskDatabase getInstance(Application application) {
        if(sInstance == null) {
            sInstance = Room.databaseBuilder(
                    application.getApplicationContext(),
                    TaskDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return sInstance;
    }

    public abstract TaskDao taskDao();
}
