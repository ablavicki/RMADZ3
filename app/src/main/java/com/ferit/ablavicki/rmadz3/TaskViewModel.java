package com.ferit.ablavicki.rmadz3;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<List<Category>> mAllCategories;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
        mAllCategories = mRepository.getAllCategories();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    public void insertTask(Task task){ mRepository.insertTask(task);}

    public void deleteTask(Task task) {
        mRepository.deleteTask(task);
    }

    public LiveData<List<Category>> getAllCategories() {
        return mAllCategories;
    }

    public void insertCategory(Category category){
        mRepository.insertCategory(category);
    }

    public void deleteCategory(Category category){
        mRepository.deleteCategory(category);
    }
}
