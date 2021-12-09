package com.example.birthdayapp.main

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.birthdayapp.database.Birthday
import java.text.SimpleDateFormat

@BindingAdapter("birthdayDate")
fun TextView.setDate(item: Birthday) {
    text = SimpleDateFormat.getDateInstance().format(item.date)
}

@BindingAdapter("birthdayName")
fun TextView.setName(item: Birthday) {
    text = item.name
}