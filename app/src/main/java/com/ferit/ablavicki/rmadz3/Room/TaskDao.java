package com.ferit.ablavicki.rmadz3.Room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ferit.ablavicki.rmadz3.Category;
import com.ferit.ablavicki.rmadz3.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert (Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * FROM task_table ORDER BY priority ASC")
    LiveData<List<Task>> getAllTasks();

    /*@Query("SELECT category FROM category_table ORDER BY category ASC")
    LiveData<List<Category>> getAllCategories();*/
}
