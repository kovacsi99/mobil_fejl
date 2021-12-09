package com.example.birthdayapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

@Entity(tableName = "birthday_table")
data class Birthday(
    @ColumnInfo(name = "name")
    var name: String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "date")
    var date: Date
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}
