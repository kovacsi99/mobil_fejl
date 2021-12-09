package com.example.birthdayapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BirthdayDatabaseDao {
    @Insert
    suspend fun insert(birthday: Birthday): Unit

    @Query("DELETE FROM birthday_table WHERE id = :id")
    suspend fun deleteById(id: Long): Unit

    @Query("DELETE FROM birthday_table")
    suspend fun clear()

    @Query("SELECT * FROM birthday_table ORDER BY name DESC")
    fun selectAll(): LiveData<List<Birthday>>

    @Query("SELECT * from birthday_table WHERE id = :id")
    fun selectById(id: Long): Birthday
}