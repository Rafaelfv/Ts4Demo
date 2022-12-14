package com.grupo.jibaro.tienditas_repartidor.injection.component

import com.example.ts4_demo.ui.viewModels.SignInViewModel
import com.example.ts4_demo.ui.viewModels.SignUpViewModel
import com.grupo.jibaro.tienditas_repartidor.injection.module.NetworkModule
import dagger.Component

@Component(modules = [(NetworkModule::class)])
interface ComponentlInjector {

    /**
     * Injector de dependencias para los viewModels
     */
    fun inject(signInViewModel: SignInViewModel)
    fun inject(signUpViewModel: SignUpViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ComponentlInjector
        fun networkModule(networkModule:NetworkModule): Builder
    }
}