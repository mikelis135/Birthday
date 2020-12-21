package com.birthday.data.remote

import com.birthday.database.entity.BirthdayData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DefaultNetworkRepository @Inject constructor(
    private val service: ApiService
) : NetworkRepository {

    override fun birthdays(
        page: Int,
        onSuccess: (birthdayData: BirthdayData) -> Unit,
        onError: (error: String) -> Unit
    ) {

        service.getBirthdays(page).enqueue(object : Callback<BirthdayData> {
            override fun onFailure(call: Call<BirthdayData>, t: Throwable) {
                onError("${t.message}")
            }

            override fun onResponse(
                call: Call<BirthdayData>,
                response: Response<BirthdayData>
            ) {
                response.body()?.let { onSuccess(it) }
            }

        })
    }

}

