package com.github.skomaromi.tasky.ui;

import com.github.skomaromi.tasky.model.Task;

public interface TaskClickCallback {
    boolean onLongClick(Task task);
}
