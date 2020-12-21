package com.birthday.database.converter

import androidx.room.TypeConverter
import com.birthday.database.entity.UserName
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserNameConverter {

    val gson = Gson()

    @TypeConverter
    fun fromUserName(userName: UserName): String {

        val type = object : TypeToken<UserName>() {

        }.type
        return gson.toJson(userName, type)
    }

    @TypeConverter
    fun toUserName(userNameString: String): UserName {
        val type = object : TypeToken<UserName>() {

        }.type
        return gson.fromJson(userNameString, type)
    }

}