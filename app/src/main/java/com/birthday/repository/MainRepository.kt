package com.birthday.repository

import androidx.lifecycle.LiveData
import com.birthday.database.entity.Birthday

interface MainRepository {

    fun getBirthdays(
        page: Int,
        birthdayResponse: (birthday: List<Birthday>) -> Unit,
        error: (error: String) -> Unit
    )

    suspend fun saveBirthdays(
        birthdayResponse: List<Birthday>,
        birthdayLiveData: (birthday: LiveData<List<Birthday>>) -> Unit
    )

    fun getLocalBirthdays(): LiveData<List<Birthday>>

}