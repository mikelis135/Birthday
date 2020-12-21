package com.birthday.ui

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

    var birthdayLiveDta: LiveData<Birthday> = mainRepository.getLocalBirthdays()

    var birthdayError: MutableLiveData<String> = MutableLiveData()


    init {
        processBirthday()
    }

    fun processBirthday(page: Int = 1) {

        viewModelScope.launch {
            mainRepository.getBirthdays(page, {
                saveBirthdays(it, coroutineDispatcher)
            }, {
                birthdayError.value = it
            })
        }
    }

    private fun saveBirthdays(birthdays: List<Birthday>, coroutineDispatcher: CoroutineDispatcher) {

        viewModelScope.launch(coroutineDispatcher) {
            mainRepository.saveBirthdays(birthdays) { newEpisode ->
                birthdayLiveDta = newEpisode
            }
        }
    }


}