package com.birthday.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.birthday.database.entity.Birthday

@Dao
interface BirthdayDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBirthday(birthday: List<Birthday>)

    @Update
    suspend fun updateBirthday(birthday: List<Birthday>)

    @Query("select * from birthdayTable")
    fun getBirthday(): LiveData<List<Birthday>>

}