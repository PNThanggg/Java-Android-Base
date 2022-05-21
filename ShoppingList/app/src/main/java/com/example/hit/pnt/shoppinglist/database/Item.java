package com.example.hit.pnt.shoppinglist.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "itemName")
    private String itemName;

    @ColumnInfo(name = "categoryId")
    private int categoryId;

    @ColumnInfo(name = "completed")
    private boolean completed;
}
