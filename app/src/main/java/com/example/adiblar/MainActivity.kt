package com.example.adiblar

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.adiblar.sharedPreference.MySharedPreferences
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var mySharedPreference: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        mySharedPreference = MySharedPreferences(this)
        setLocale()

        if (mySharedPreference.getBoolean() == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun setLocale() {
        val locale = Locale(mySharedPreference.getLang())
        Locale.setDefault(locale)
        val config: Configuration = resources.configuration
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}