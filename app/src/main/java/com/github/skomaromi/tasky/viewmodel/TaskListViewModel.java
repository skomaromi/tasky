package com.github.skomaromi.tasky.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.github.skomaromi.tasky.TaskRepository;
import com.github.skomaromi.tasky.model.Task;

import java.util.List;

public class TaskListViewModel extends ViewModel {
    TaskRepository mRepository;

    public TaskListViewModel() {
        mRepository = TaskRepository.getInstance();
    }

    public LiveData<List<Task>> getTaskList() {
        return mRepository.getTasks();
    }

    public void insertTask(Task task) {
        mRepository.insertTask(task);
    }

    public void deleteTask(Task task) {
        mRepository.deleteTask(task);
    }
}
