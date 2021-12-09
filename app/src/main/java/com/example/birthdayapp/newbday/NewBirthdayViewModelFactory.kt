package com.example.birthdayapp.newbday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.birthdayapp.database.BirthdayRepository

class NewBirthdayViewModelFactory(
    private val repository: BirthdayRepository
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewBirthdayViewModel::class.java)) {
            return NewBirthdayViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}