package com.example.rurunote_1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1,exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {
    abstract val dao: ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null
        fun getInstance(context: Context): ItemDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java,
                        "item_database"
                    ).build()
                }
                return instance
            }
        }
    }
}