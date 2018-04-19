package com.github.skomaromi.tasky.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.skomaromi.tasky.R;
import com.github.skomaromi.tasky.model.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnLongClick;

class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> mTasks;
    private TaskClickCallback mCallback;

    public TaskAdapter(List<Task> tasks, TaskClickCallback onTaskClickListener) {
        mTasks = new ArrayList<>();
        this.refreshData(tasks);
        mCallback = onTaskClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull final TaskViewHolder holder, final int position) {
        final Task current = mTasks.get(position);

        int ribbonColorId;
        switch(current.getPriority()) {
            case "high":
                ribbonColorId = R.color.priorityHigh;
                break;
            case "medium":
                ribbonColorId = R.color.priorityMedium;
                break;
            default:
                ribbonColorId = R.color.priorityLow;
                break;
        }
        holder.ivPriorityRibbon.setBackgroundResource(ribbonColorId);

        holder.tvTaskTitle.setText(current.getTitle());
        holder.tvTaskDescription.setText(current.getDescription());
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public void refreshData(List<Task> tasks) {
        mTasks.clear();
        mTasks.addAll(tasks);
        this.notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPriorityRibbon) ImageView ivPriorityRibbon;
        @BindView(R.id.tvTaskTitle) TextView tvTaskTitle;
        @BindView(R.id.tvTaskDescription) TextView tvTaskDescription;

        TaskClickCallback mCallback;

        public TaskViewHolder(
                final View itemView,
                final TaskClickCallback callback
        ) {
           super(itemView);
           mCallback = callback;
           ButterKnife.bind(this, itemView);
        }

        @OnLongClick
        public boolean onTaskLongClick() {
            return mCallback.onLongClick(mTasks.get(getAdapterPosition()));
        }
    }
}
