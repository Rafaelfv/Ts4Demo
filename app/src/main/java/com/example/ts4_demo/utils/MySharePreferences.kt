package com.example.ts4_demo.utils

import android.content.Context
import android.content.SharedPreferences

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

    fun setIdToken(token: String) {
        editor.putString("idToken", token).commit()
    }

    fun getIdToken(): String {
        return preferences.getString("idToken", "").toString()
    }

    fun setSalesforceToken(token: String) {
        editor.putString("salesforceToken", token).commit()
    }

    fun getSalesforceToken(): String {
        return preferences.getString("salesforceToken", "").toString()
    }

    fun setRefreshToken(token: String) {
        editor.putString("refreshToken", token).commit()
    }

    fun getRefreshToken(): String {
        return preferences.getString("refreshToken", "").toString()
    }

    fun setSalesUserId(token: String) {
        editor.putString("salesforceUI", token).commit()
    }

    fun getSalesforceUserId(): String {
        return preferences.getString("salesforceUI", "").toString()
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