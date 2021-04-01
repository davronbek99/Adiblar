package com.example.adiblar.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WriterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "writer")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }

    abstract fun writerDao(): WriterDao
}