package com.example.birthdayapp.main

import com.example.birthdayapp.database.Birthday

class OnItemClickListener (val clickListener: (birthday: Birthday) -> Unit) {
    fun onClick(todo: Birthday) = clickListener(todo)
}