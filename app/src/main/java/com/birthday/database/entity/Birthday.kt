package com.birthday.database.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.birthday.app.AppConstants

@Entity(tableName = AppConstants.BIRTHDAY_TABLE)
class Birthday(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: UserName,
    val dob: DateOfBirth
)

data class BirthdayData(
    val results: List<Birthday>
)

data class UserName(
    val title: String,
    val first: String,
    val last: String
)

data class DateOfBirth(
    val date: String,
    val age: String
)
