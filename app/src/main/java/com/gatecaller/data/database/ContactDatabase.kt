package com.gatecaller.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gatecaller.data.entity.ApiContactDatabase

@Database(entities = [ApiContactDatabase::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun newsDao(): ContactDao
    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ContactDatabase::class.java, "contacts"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}