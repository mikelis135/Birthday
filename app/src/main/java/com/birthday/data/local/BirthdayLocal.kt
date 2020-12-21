package com.birthday.data.local

import androidx.lifecycle.LiveData
import com.birthday.database.entity.Birthday

interface BirthdayLocal {

    suspend fun saveBirthday(birthday: List<Birthday>)

    suspend fun updateBirthday(birthday: List<Birthday>)

    fun getBirthday(): LiveData<Birthday>

}