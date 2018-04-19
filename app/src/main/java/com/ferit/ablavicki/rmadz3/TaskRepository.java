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

    TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks(){
        return mAllTasks;
    }

    public void insert(Task task){
        new insertAsyncTask(mTaskDao).execute(task);
    }

    private static class insertAsyncTask extends AsyncTask<Task, Void, Void>{
        private TaskDao mAsyncTaskDao;
        insertAsyncTask(TaskDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... tasks) {
            mAsyncTaskDao.insert(tasks[0]);
            return null;
        }
    }
}
