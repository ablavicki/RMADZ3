package com.ferit.ablavicki.rmadz3;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "category_table")
public class Category {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "category")
    private String categoryName;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    @NonNull
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(@NonNull String categoryName) {
        this.categoryName = categoryName;
    }
}
