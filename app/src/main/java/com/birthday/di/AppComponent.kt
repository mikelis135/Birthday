package com.birthday.di

import com.birthday.ui.main.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, RemoteModule::class])

interface AppComponent {
    fun inject(mainFragment: MainFragment)
}