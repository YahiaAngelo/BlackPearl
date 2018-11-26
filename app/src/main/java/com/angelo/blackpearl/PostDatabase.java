package com.angelo.blackpearl;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
public abstract class PostDatabase extends RoomDatabase {

    private static PostDatabase INSTANCE;

    public abstract PostDao postDao();

    private static final Object sLock = new Object();

    public static PostDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PostDatabase.class, "posts.db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }
}