package com.ferit.ablavicki.rmadz3;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToDoActivity extends AppCompatActivity {

    public static final String TASK_NAME = "name";
    public static final String TASK_CATEGORY = "category";
    public static final String TASK_PRIORITY = "priority";
    public static final int ADD_TASK_ACTIVITY_REQUEST_CODE = 1;

    @BindView(R.id.rvToDo)
    RecyclerView rvToDo;

    TaskAdapter mTaskAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.ItemDecoration mItemDecoration;
    private TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        ButterKnife.bind(this);
        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        mTaskViewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                mTaskAdapter.setTasks(tasks);
            }
        });
        mTaskViewModel.insertTask(new Task(R.color.highPriority, "rma", "school"));

        setupRV();
    }

    private void setupRV() {
        mTaskAdapter = new TaskAdapter(new ArrayList<Task>(), onTaskClickListener);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        rvToDo.addItemDecoration(mItemDecoration);
        rvToDo.setLayoutManager(mLayoutManager);
        rvToDo.setAdapter(mTaskAdapter);
    }

    @OnClick(R.id.fabAddTask)
    public void AddTask(){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, ADD_TASK_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Task task = new Task(data.getIntExtra(TASK_PRIORITY, 0), data.getStringExtra(TASK_NAME), data.getStringExtra(TASK_CATEGORY));
            mTaskViewModel.insertTask(task);
        }
    }

    private ClickCallback onTaskClickListener = new ClickCallback() {
        @Override
        public boolean onLongClick(Task task) {
            mTaskViewModel.deleteTask(task);
            return true;
        }
    };
}
