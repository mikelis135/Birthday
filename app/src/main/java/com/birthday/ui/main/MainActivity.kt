package com.birthday.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.birthday.R
import com.birthday.helper.Tools
import com.birthday.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Tools.gotoFragmentOnly(this, MainFragment())
    }
}