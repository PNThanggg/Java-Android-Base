package com.example.hit.pnt.shoppinglist.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name= "categoryName")
    private String categoryName;


}
