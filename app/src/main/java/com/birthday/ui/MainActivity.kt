package com.birthday.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.birthday.R
import com.birthday.helper.Tools

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Tools.gotoFragmentOnly(this, MainFragment())
    }
}