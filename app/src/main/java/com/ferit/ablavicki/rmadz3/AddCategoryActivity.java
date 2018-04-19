package com.ferit.ablavicki.rmadz3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCategoryActivity extends AppCompatActivity {

    @BindView(R.id.etCategory)
    EditText etCategory;

    @BindView(R.id.bAddNewCategory)
    Button bAddNewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bAddNewCategory)
    public void addTask(View view){
        Intent Intent = new Intent();
        if(TextUtils.isEmpty(etCategory.getText())){
            setResult(RESULT_CANCELED, Intent);
        }
        else{
            String category = etCategory.getText().toString();
            Intent.putExtra(ToDoActivity.TASK_CATEGORY, category);
            setResult(RESULT_OK, Intent);
        }
        finish();
    }
}
