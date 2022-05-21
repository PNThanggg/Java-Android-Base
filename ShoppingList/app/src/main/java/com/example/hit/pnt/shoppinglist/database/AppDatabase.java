package com.example.hit.pnt.shoppinglist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Category.class, Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTANCE;

    public static AppDatabase getDatabaseInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "AppDatabase")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
