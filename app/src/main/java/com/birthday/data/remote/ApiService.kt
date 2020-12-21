package com.birthday.data.remote

import com.birthday.database.entity.BirthdayData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/?results=10&seed=chalkboard&inc=name,dob")
    fun getBirthdays(@Query("page") page: Int = 1): Call<BirthdayData>

}