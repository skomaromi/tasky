package com.github.skomaromi.tasky;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.github.skomaromi.tasky.model.Task;
import com.github.skomaromi.tasky.persistence.room.TaskDao;
import com.github.skomaromi.tasky.persistence.room.TaskDatabase;

import java.util.List;

public class TaskRepository {
    private static TaskRepository sInstance;
    private TaskDatabase mDatabase;
    private LiveData<List<Task>> mData;

    private TaskRepository(Application application) {
        mDatabase = TaskDatabase.getInstance(application);
        mData = mDatabase.taskDao().getTasks();
    }

    public static TaskRepository getInstance() {
        if (sInstance == null) {
            sInstance = new TaskRepository(TaskyApp.getInstance());
        }
        return sInstance;
    }

    public LiveData<List<Task>> getTasks() {
        return mData;
    }

    public void insertTask(Task task) {
        new insertTaskAsyncT(mDatabase.taskDao()).execute(task);
    }

    public void deleteTask(Task task) {
        new deleteTaskT(mDatabase.taskDao()).execute(task);
    }

    private class insertTaskAsyncT extends AsyncTask<Task, Void, Void> {
        private TaskDao mTaskDao;

        public insertTaskAsyncT(TaskDao taskDao) {
            mTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTaskDao.insertTask(tasks[0]);
            return null;
        }
    }

    private class deleteTaskT extends AsyncTask<Task, Void, Void> {
        private TaskDao mTaskDao;

        public deleteTaskT(TaskDao taskDao) {
            this.mTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            mTaskDao.deleteTask(tasks[0]);
            return null;
        }

    }


}
