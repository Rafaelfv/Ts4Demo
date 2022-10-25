package com.example.ts4_demo

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.grupo.jibaro.tienditas_repartidor.injection.component.ComponentlInjector
import com.grupo.jibaro.tienditas_repartidor.injection.component.DaggerComponentlInjector
import com.grupo.jibaro.tienditas_repartidor.injection.module.NetworkModule
import com.grupo.jibaro.tienditas_repartidor.utils.MySharePreferences

class Ts4Application : Application() {

    companion object {
        lateinit var context: Context
        lateinit var component: ComponentlInjector
        lateinit var mInstance: Ts4Application
        lateinit var resourceManager: AndroidResourceManager

        fun getAppContext(): Context {
            return context
        }

    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        MySharePreferences.init(this)
        resourceManager = AndroidResourceManager(resources)
        component = DaggerComponentlInjector.builder().networkModule(NetworkModule).build()
    }

    @Synchronized
    fun getInstance(): Ts4Application {
        return mInstance
    }

    fun getComponentNetwork(): ComponentlInjector {
        return component
    }

    class AndroidResourceManager(private val resources: Resources) {

        val getAppName: String
            get() = resources.getString(R.string.app_name)

        val getEmptyMessage: String
            get() = resources.getString(R.string.empty_field)

        val getErrorServer: String
            get() = resources.getString(R.string.error_server)

    }
}