package com.ferit.ablavicki.rmadz3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "task_table")
public class Task {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "task")
    private String name;

    @NonNull
    @ColumnInfo(name = "priority")
    private int priority;

    @NonNull
    @ColumnInfo(name = "category")
    private String category;

    public Task(int priority, String name, String category) {
        this.priority = priority;
        this.name = name;
        this.category = category;
    }

    public int getPriority() { return priority; }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
