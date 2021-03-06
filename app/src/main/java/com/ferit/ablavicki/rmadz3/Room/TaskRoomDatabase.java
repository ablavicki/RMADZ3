package com.ferit.ablavicki.rmadz3.Room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ferit.ablavicki.rmadz3.Category;
import com.ferit.ablavicki.rmadz3.R;
import com.ferit.ablavicki.rmadz3.Task;

@Database(entities = {Task.class, Category.class}, version=1)
public abstract class TaskRoomDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    private static TaskRoomDatabase INSTANCE;

    public static TaskRoomDatabase getDatabase(final Context context){
        if(INSTANCE ==null){
            synchronized (TaskRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class, "task_database")
                            .addCallback(sRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallBack =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TaskDao mDao;

        PopulateDbAsync(TaskRoomDatabase db) {
            mDao = db.taskDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Category category = new Category("Personal");
            mDao.insertCategory(category);
            category = new Category("School");
            mDao.insertCategory(category);
            category = new Category("Work");
            mDao.insertCategory(category);
            category = new Category("Other");
            mDao.insertCategory(category);
            return null;
        }
    }
}
