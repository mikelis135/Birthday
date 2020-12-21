package com.birthday.data.remote

import com.birthday.database.entity.BirthdayData

interface NetworkRepository {

    fun birthdays(
        page: Int,
        onSuccess: (birthdayData: BirthdayData) -> Unit,
        onError: (error: String) -> Unit
    )

}