package com.birthday.database.converter

import androidx.room.TypeConverter
import com.birthday.database.entity.DateOfBirth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DobConverter {

    val gson = Gson()

    @TypeConverter
    fun fromDateOfBirth(dateOfBirth: DateOfBirth): String {

        val type = object : TypeToken<DateOfBirth>() {

        }.type
        return gson.toJson(dateOfBirth, type)
    }

    @TypeConverter
    fun toDateOfBirth(dateOfBirthString: String): DateOfBirth {
        val type = object : TypeToken<DateOfBirth>() {

        }.type
        return gson.fromJson(dateOfBirthString, type)
    }

}