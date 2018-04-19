package com.github.skomaromi.tasky.ui;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.skomaromi.tasky.R;
import com.github.skomaromi.tasky.model.Task;
import com.github.skomaromi.tasky.viewmodel.TaskListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskDisplayActivity extends AppCompatActivity {
    @BindView(R.id.rvTasks) RecyclerView rvTasks;
    @BindView(R.id.fabAddTask) FloatingActionButton fabAddTask;

    private TaskListViewModel mTaskListViewModel;

    private TaskClickCallback mOnTaskClickListener = new TaskClickCallback() {
        @Override
        public boolean onLongClick(Task task) {
            mTaskListViewModel.deleteTask(task);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_display);

        ButterKnife.bind(this);

        mTaskListViewModel = ViewModelProviders
                .of(this)
                .get(TaskListViewModel.class);
        this.setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayout = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        );

        DividerItemDecoration divider = new DividerItemDecoration(
                this,
                linearLayout.getOrientation()
        );

        TaskAdapter adapter = new TaskAdapter(
                new ArrayList<Task>(),
                mOnTaskClickListener
        );

        rvTasks.setLayoutManager(linearLayout);
        rvTasks.addItemDecoration(divider);
        rvTasks.setAdapter(adapter);

        mTaskListViewModel.getTaskList().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                ((TaskAdapter)(rvTasks.getAdapter())).refreshData(tasks);
            }
        });
    }

    @OnClick(R.id.fabAddTask)
    public void addTask() {
        Intent taskAddActivity = new Intent(this, TaskAddActivity.class);
        startActivityForResult(taskAddActivity, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String title, description, priority;

                title = data.getStringExtra(TaskAddActivity.KEY_TITLE);
                description = data.getStringExtra(TaskAddActivity.KEY_DESC);
                priority = data.getStringExtra(TaskAddActivity.KEY_PRIORITY);

                Task task = new Task(title, description, priority);
                mTaskListViewModel.insertTask(task);
            }
        }
    }
}
