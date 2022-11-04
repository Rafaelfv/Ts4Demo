package com.example.ts4_demo

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.ts4_demo.injection.component.ComponentlInjector
import com.example.ts4_demo.injection.component.DaggerComponentlInjector
import com.example.ts4_demo.injection.module.NetworkModule
import com.example.ts4_demo.utils.MySharePreferences


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

        val getLoginSuccess: String
            get() = resources.getString(R.string.loggin_success)

        val getNoVerify: String
            get() = resources.getString(R.string.no_verify)

        val getEmptyMessage: String
            get() = resources.getString(R.string.empty_field)

        val getErrorServer: String
            get() = resources.getString(R.string.error_server)

        val getUserAlreadyUsed: String
            get() = resources.getString(R.string.username_already_used)

        val getTokenInvalid: String
            get() = resources.getString(R.string.token_invalid)

        val getSingUpSuccess: String
            get() = resources.getString(R.string.signup_success)

        val getPasswordNotEquals: String
            get() = resources.getString(R.string.passwords_not_equals)


    }
}