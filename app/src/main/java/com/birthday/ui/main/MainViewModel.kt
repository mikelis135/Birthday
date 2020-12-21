package com.birthday.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.birthday.database.entity.Birthday
import com.birthday.repository.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val mainRepository: MainRepository
) :
    ViewModel() {

    var birthdayLiveData: LiveData<List<Birthday>> = mainRepository.getLocalBirthdays()

    var errorLiveData: MutableLiveData<String> = MutableLiveData()


    init {
        processBirthday()
    }

    fun processBirthday(page: Int = 1) {

        viewModelScope.launch {
            mainRepository.getBirthdays(page, {
                saveBirthdays(it, coroutineDispatcher)
            }, {
                errorLiveData.value = it
            })
        }
    }

    private fun saveBirthdays(birthdays: List<Birthday>, coroutineDispatcher: CoroutineDispatcher) {

        viewModelScope.launch(coroutineDispatcher) {
            mainRepository.saveBirthdays(birthdays) { birthday ->
                birthdayLiveData = birthday
            }
        }
    }


}