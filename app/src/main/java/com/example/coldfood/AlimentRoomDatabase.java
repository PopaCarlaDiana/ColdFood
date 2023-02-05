package com.example.coldfood;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.coldfood.converter.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Aliment.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AlimentRoomDatabase extends RoomDatabase {

    public abstract AlimentDao alimentDao();

    private static volatile AlimentRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static AlimentRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AlimentRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AlimentRoomDatabase.class, "aliment_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
