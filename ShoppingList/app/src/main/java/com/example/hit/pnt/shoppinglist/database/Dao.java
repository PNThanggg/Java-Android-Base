package com.example.hit.pnt.shoppinglist.database;

import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("select * from Category")
    List<Category> getAllCategory();
}
