package com.example.birthdayapp.date

import android.util.Log
import java.util.*

fun dayOfYear(year: Int, month: Int, day: Int): Int {
    val calendar: Calendar = GregorianCalendar(year, month, day)

    Log.d("Imi", "${year} ${month} ${day} is ${calendar.get(Calendar.DAY_OF_YEAR)}th of year")
    return calendar.get(Calendar.DAY_OF_YEAR)
}