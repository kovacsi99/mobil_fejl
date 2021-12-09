package com.example.birthdayapp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.birthdayapp.database.Birthday
import com.example.birthdayapp.database.BirthdayRepository

class MainFragmentViewModel (
    private val repository: BirthdayRepository
    ) : ViewModel() {
    val items = repository.selectAll()
    private val _navigateToNew = MutableLiveData<Boolean?>()

    val navigateToNew: LiveData<Boolean?>
    get() = _navigateToNew

    fun doneNavigating() {
        _navigateToNew.value = null
    }

    fun onNew() {
        _navigateToNew.value = true
    }
}