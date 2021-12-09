package com.example.birthdayapp.newbday

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.birthdayapp.database.Birthday
import com.example.birthdayapp.database.BirthdayRepository
import kotlinx.coroutines.launch
import java.util.*

class NewBirthdayViewModel (
    private val repository: BirthdayRepository
    ) : ViewModel() {

    var date: Date? = null

    private val _navigateBack = MutableLiveData<Boolean?>()

    val navigateBack: LiveData<Boolean?>
        get() = _navigateBack

    fun doneNavigating() {
        _navigateBack.value = null
    }

    fun onCancel() {
        viewModelScope.launch {
            _navigateBack.value = true
        }
    }

    fun onOk(name: String) {
        if (name.length.equals(0))
            return

        if (date == null)
            return

        viewModelScope.launch {
            Log.d("Imi", "${name}@${date.toString()}")

            repository.insert(Birthday(name, date!!))
            _navigateBack.value = true
        }
    }

}