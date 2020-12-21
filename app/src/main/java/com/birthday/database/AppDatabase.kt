package com.birthday.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.birthday.database.converter.BirthdayListConverter
import com.birthday.database.converter.DobConverter
import com.birthday.database.converter.UserNameConverter
import com.birthday.database.dao.BirthdayDAO
import com.birthday.database.entity.Birthday

@Database(
    entities = [Birthday::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(UserNameConverter::class, DobConverter::class, BirthdayListConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun birthdayDAO(): BirthdayDAO

}