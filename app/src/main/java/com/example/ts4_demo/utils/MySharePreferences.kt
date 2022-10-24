package com.grupo.jibaro.tienditas_repartidor.utils

import android.content.Context
import android.content.SharedPreferences

class MySharePreferences(context: Context) {
    private val sharedPreference: SharedPreferences =  context.getSharedPreferences("Ts4App",Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreference.edit()

    fun setToken(token:String){
        editor.putString("token",token).commit()
    }
    fun getToken(): String{
        return sharedPreference.getString("token","").toString()
    }

    fun setAlreadyDelivery(registered: Boolean){
        editor.putBoolean("alreadyDeliveryStores",registered).commit()
    }

    fun isAlreadyDelivery(): Boolean{
        return sharedPreference.getBoolean("alreadyDeliveryStores",false)
    }

    fun setItemStoreSelected(position:Int){
        editor.putInt("storeSelected",position).commit()
    }

    fun getItemStoreSelected():Int{
        return sharedPreference.getInt("storeSelected",0)
    }

    fun clearAll(){
        sharedPreference.edit().clear().apply()
    }
}