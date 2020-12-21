package com.birthday.database.converter

import androidx.room.TypeConverter
import com.birthday.database.entity.Birthday
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BirthdayListConverter {

    val gson = Gson()

    @TypeConverter
    fun fromBirthdayList(birthdayList: List<Birthday>): String {

        val type = object : TypeToken<List<Birthday>>() {

        }.type
        return gson.toJson(birthdayList, type)
    }

    @TypeConverter
    fun toBirthdayList(birthdayString: String): List<Birthday> {
        val type = object : TypeToken<List<Birthday>>() {

        }.type
        return gson.fromJson(birthdayString, type)
    }

}