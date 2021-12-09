package com.example.birthdayapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Birthday::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BirthdayDatabase: RoomDatabase() {
    abstract val birthdayDatabaseDao: BirthdayDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: BirthdayDatabase? = null

        fun getInstance(context: Context): BirthdayDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BirthdayDatabase::class.java,
                        "birthday_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}