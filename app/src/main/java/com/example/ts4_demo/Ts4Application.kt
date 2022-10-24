package com.example.ts4_demo

import android.app.Application
import android.content.Context
import com.grupo.jibaro.tienditas_repartidor.injection.component.ComponentlInjector
import com.grupo.jibaro.tienditas_repartidor.injection.component.DaggerComponentlInjector
import com.grupo.jibaro.tienditas_repartidor.injection.module.NetworkModule
import com.grupo.jibaro.tienditas_repartidor.utils.MySharePreferences

class Ts4Application: Application() {

    companion object {
        lateinit var context: Context
        lateinit var component: ComponentlInjector
        lateinit var share: MySharePreferences
        lateinit var mInstance: Ts4Application
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        share = MySharePreferences(context)
        component= DaggerComponentlInjector.builder().networkModule(NetworkModule).build()
    }

    @Synchronized
    fun getInstance(): Ts4Application {
        return mInstance
    }

    fun getComponentNetwork(): ComponentlInjector {
        return component
    }

}