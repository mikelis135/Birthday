package com.birthday.app

import android.app.Application
import com.birthday.di.AppComponent
import com.birthday.di.DaggerAppComponent
import com.birthday.di.DatabaseModule

class App : Application() {

    val appComponent by lazy {
        initialiseAppComponent()
    }

    private fun initialiseAppComponent(): AppComponent {
        val builder = DaggerAppComponent.builder()
        return builder.databaseModule(DatabaseModule(this)).build()
    }

}