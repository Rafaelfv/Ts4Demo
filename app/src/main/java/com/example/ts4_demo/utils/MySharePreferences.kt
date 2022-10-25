package com.grupo.jibaro.tienditas_repartidor.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.ts4_demo.utils.IS_LOGIN_FLAG

object MySharePreferences {
    private const val NAME = "consumer_good"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
        editor = preferences.edit()
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    fun setLogin(value: Boolean) {
        editor.putBoolean(IS_LOGIN_FLAG, value).commit()
    }

    fun isLogin(): Boolean {
        return preferences.getBoolean(IS_LOGIN_FLAG, false)
    }

    fun setToken(token: String) {
        editor.putString("token", token).commit()
    }

    fun getToken(): String {
        return preferences.getString("token", "").toString()
    }

    fun setAlreadyDelivery(registered: Boolean) {
        editor.putBoolean("alreadyDeliveryStores", registered).commit()
    }

    fun isAlreadyDelivery(): Boolean {
        return preferences.getBoolean("alreadyDeliveryStores", false)
    }

    fun setItemStoreSelected(position: Int) {
        editor.putInt("storeSelected", position).commit()
    }

    fun getItemStoreSelected(): Int {
        return preferences.getInt("storeSelected", 0)
    }

    fun removePreferences() {
        preferences.edit().clear().apply()
    }
}