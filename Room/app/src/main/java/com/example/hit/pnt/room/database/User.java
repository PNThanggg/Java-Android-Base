package com.example.hit.pnt.room.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name= "first_name")
    public String first_name;

    @ColumnInfo(name= "last_name")
    public String last_name;

    public User() {
    }

}
