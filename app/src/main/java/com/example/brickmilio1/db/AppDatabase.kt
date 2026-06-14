package com.example.brickmilio1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.brickmilio1.dao.LegoSetDao
import com.example.brickmilio1.model.LegoSet

@Database(entities = [LegoSet::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun legoSetDao(): LegoSetDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "brickmilio-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}