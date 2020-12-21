package com.birthday.data.local

import androidx.lifecycle.LiveData
import com.birthday.database.dao.BirthdayDAO
import com.birthday.database.entity.Birthday
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultBirthdayLocal(
    private val birthdayDAO: BirthdayDAO,
    private val coroutineDispatcher: CoroutineDispatcher
) : BirthdayLocal {

    override suspend fun saveBirthday(birthday: List<Birthday>) =
        withContext(coroutineDispatcher) { birthdayDAO.saveBirthday(birthday) }

    override suspend fun updateBirthday(birthday: List<Birthday>) =
        withContext(coroutineDispatcher) { birthdayDAO.updateBirthday(birthday) }

    override fun getBirthday(): LiveData<Birthday> = birthdayDAO.getBirthday()

}