package com.birthday.helper

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.birthday.R
import java.text.SimpleDateFormat
import java.util.*

object Tools {

    fun toDate(
        dateString: String,
        dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
        timeZone: TimeZone = TimeZone.getTimeZone("UTC")
    ): String? {

        return try {
            val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
            val formatter = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            parser.timeZone = timeZone
            formatter.timeZone = timeZone
            formatter.format(parser.parse(dateString))
        } catch (ignore: Exception) {
            ""
        }
    }

    fun getFormattedDateEvent(dateTime: Long): String {
        val newFormat = SimpleDateFormat("EEE, MMM dd yyyy")
        return newFormat.format(Date(dateTime))
    }

    fun getFormattedTimeEvent(time: Long): String {
        val newFormat = SimpleDateFormat("h:mm a")
        return newFormat.format(Date(time))
    }

    fun gotoFragment(activity: FragmentActivity, fragment: Fragment, tag: String = "") {
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.container_body, fragment, tag).addToBackStack("")
            .commit()
    }

    fun gotoFragmentOnly(activity: FragmentActivity, fragment: Fragment, tag: String = "") {
        activity.supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
            .replace(R.id.container_body, fragment, tag)
            .commit()
    }

}
