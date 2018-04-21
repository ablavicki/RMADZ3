package com.ferit.ablavicki.rmadz3;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.ferit.ablavicki.rmadz3.Room.TaskDao;
import com.ferit.ablavicki.rmadz3.Room.TaskRoomDatabase;

import java.util.List;

public class TaskRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<List<Category>> mAllCategories;

    TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getAllTasks();
        mAllCategories = mTaskDao.getAllCategories();
    }

    LiveData<List<Task>> getAllTasks(){
        return mAllTasks;
    }

    public void insertTask(Task task){
        new insertTaskAsyncTask(mTaskDao).execute(task);
    }

    public void deleteTask(Task task) {
        new deleteTaskAsyncTask(mTaskDao).execute(task);
    }

    LiveData<List<Category>> getAllCategories(){ return mAllCategories; }

    public void insertCategory(Category category) { new insertCategoryAsyncTask(mTaskDao).execute(category);}

    public void deleteCategory(Category category) { new deleteCategoryAsyncTask(mTaskDao).execute(category);}

    private static class insertTaskAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDao mAsyncTaskDao;
        insertTaskAsyncTask(TaskDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.insertTask(params[0]);
            return null;
        }
    }

    private static class deleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mAsyncTaskDao;
        deleteTaskAsyncTask(TaskDao dao){ mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.deleteTask(params[0]);
            return null;
        }
    }

    private static class insertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {
        private TaskDao mAsyncTaskDao;

        insertCategoryAsyncTask(TaskDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            mAsyncTaskDao.insertCategory(params[0]);
            return null;
        }
    }

    private static class deleteCategoryAsyncTask extends AsyncTask<Category, Void, Void>{
        private TaskDao mAsyncTaskDao;

        deleteCategoryAsyncTask (TaskDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            mAsyncTaskDao.deleteCategory(params[0]);
            return null;
        }
    }
}
