package com.birthday.repository

import androidx.lifecycle.LiveData
import com.birthday.data.local.BirthdayLocal
import com.birthday.data.remote.NetworkRepository
import com.birthday.database.entity.Birthday
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val birthdayLocal: BirthdayLocal
) : MainRepository {

    private val birthdayLd: LiveData<Birthday> = birthdayLocal.getBirthday()

    override fun getBirthdays(
        page: Int,
        birthdayResponse: (birthday: List<Birthday>) -> Unit,
        error: (error: String) -> Unit
    ) {
        networkRepository.birthdays(page, { remoteBirthdays ->

            birthdayResponse(remoteBirthdays.results)

        }, {
            error(it)
        })

    }

    override suspend fun saveBirthdays(
        birthdayResponse: List<Birthday>,
        birthdayLiveData: (channelCategory: LiveData<Birthday>) -> Unit
    ) {

        coroutineScope {

            launch {
                if (birthdayLd.value == null) {
                    birthdayLocal.saveBirthday(birthdayResponse)
                    birthdayLiveData(birthdayLocal.getBirthday())
                } else {
                    birthdayLocal.updateBirthday(birthdayResponse)
                    birthdayLiveData(birthdayLocal.getBirthday())
                }
            }

        }

    }

    override fun getLocalBirthdays() = birthdayLocal.getBirthday()

}