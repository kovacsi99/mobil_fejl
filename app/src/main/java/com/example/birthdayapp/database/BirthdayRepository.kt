package com.example.birthdayapp.database

class BirthdayRepository (
    private val birthdayDatabaseDao: BirthdayDatabaseDao) {

    suspend fun insert(birthday: Birthday) = birthdayDatabaseDao.insert(birthday)
    suspend fun deleteById(id: Long) = birthdayDatabaseDao.deleteById(id)
    suspend fun clear() = birthdayDatabaseDao.clear()

    fun selectAll() = birthdayDatabaseDao.selectAll()
    fun selectById(id: Long) = birthdayDatabaseDao.selectById(id)
}