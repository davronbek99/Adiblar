package com.example.adiblar.sharedPreference

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    private val NAME = "adib"
    private val MODE = Context.MODE_PRIVATE

    private var mySharedPreferences: SharedPreferences

    init {
        mySharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    fun setBoolean(boolean: Boolean?) {
        val editor = mySharedPreferences.edit()
        editor.putBoolean("adiblar", boolean!!)
        editor.commit()
    }

    fun getBoolean(): Boolean? {
        return mySharedPreferences.getBoolean("adiblar", false)
    }

    fun setLang(string: String?) {
        val editor = mySharedPreferences.edit()
        editor.putString("language", string!!)
        editor.commit()
    }

    fun getLang(): String? {
        return mySharedPreferences.getString("language", "uz")
    }
}