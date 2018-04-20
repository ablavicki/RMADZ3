package com.ferit.ablavicki.rmadz3;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskActivity extends AppCompatActivity {

    public static final int ADD_TASK_ACTIVITY_REQUEST_CODE = 1;
    public static final String CATEGORY_NAME = "category_name";

    @BindView(R.id.etTask)
    EditText etTask;

    @BindView(R.id.sCategory)
    Spinner sCategory;

    @BindView(R.id.sPriority)
    Spinner sPriority;

    ArrayList<String> cList;
    private TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        ButterKnife.bind(this);

        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);




        spinnerSetup();
    }

    public void spinnerSetup(){
        mTaskViewModel.getAllCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> categories) {
                cList = new ArrayList<>();
                for(Category category : categories){
                    cList.add(category.getCategoryName());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, cList);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sCategory.setAdapter(adapter);
            }

        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priority, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sPriority.setAdapter(adapter);

    }

    @OnClick(R.id.bAdd)
    public void addTask(View view){
        Intent Intent = new Intent();
        if(TextUtils.isEmpty(etTask.getText())){
            setResult(RESULT_CANCELED, Intent);
        }
        else{
            String name = etTask.getText().toString();
            String category = sCategory.getSelectedItem().toString();
            int priority = prioritySetup(sPriority.getSelectedItemPosition());
            Intent.putExtra(ToDoActivity.TASK_NAME, name);
            Intent.putExtra(ToDoActivity.TASK_CATEGORY, category);
            Intent.putExtra(ToDoActivity.TASK_PRIORITY, priority);
            setResult(RESULT_OK, Intent);
        }
        finish();
    }

    public int prioritySetup(int position){
        switch(position){
            case 0:
                return R.color.highPriority;
            case 1:
                return R.color.mediumPriority;
            case 2:
                return R.color.lowPriority;
        }
        return R.color.highPriority;
    }

    @OnClick(R.id.bAddCategory)
    public void addCategory(){
        Intent intent = new Intent(this, AddCategoryActivity.class);
        startActivityForResult(intent, ADD_TASK_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Category category = new Category(data.getStringExtra(CATEGORY_NAME));
            //mTaskViewModel.insert(task);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
