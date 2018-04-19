package com.github.skomaromi.tasky.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.skomaromi.tasky.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class TaskAddActivity extends AppCompatActivity {
    @BindView(R.id.etAddTaskTitle) EditText etAddTaskTitle;
    @BindView(R.id.etAddTaskDescription) EditText etAddTaskDescription;
    @BindView(R.id.sAddTaskPriority) Spinner sAddTaskPriority;
    @BindView(R.id.btnAddTaskOK) Button btnAddTaskOK;

    public static final String KEY_TITLE = "title";
    public static final String KEY_DESC = "description";
    public static final String KEY_PRIORITY = "priority";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnAddTaskOK.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnTextChanged(R.id.etAddTaskTitle)
    public void etAddTaskTitle_Changed() {
        textFieldsChanged();
    }

    @OnTextChanged(R.id.etAddTaskDescription)
    public void etAddTaskDescription_Changed() {
        textFieldsChanged();
    }

    public void textFieldsChanged() {
        String title, description;

        title = etAddTaskTitle.getText().toString();
        description = etAddTaskDescription.getText().toString();

        btnAddTaskOK.setEnabled(title.length() + description.length() > 0);
    }

    @OnClick(R.id.btnAddTaskOK)
    public void btnAddTaskOK_onClick() {
        String title, description, priority;

        title = etAddTaskTitle.getText().toString();
        description = etAddTaskDescription.getText().toString();
        priority = sAddTaskPriority.getSelectedItem().toString();

        Intent ret = new Intent();
        ret.putExtra(KEY_TITLE, title);
        ret.putExtra(KEY_DESC, description);
        ret.putExtra(KEY_PRIORITY, priority);

        setResult(Activity.RESULT_OK, ret);
        finish();
    }
}
