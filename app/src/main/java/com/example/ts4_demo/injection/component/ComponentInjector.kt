package com.example.ts4_demo.injection.component

import com.example.ts4_demo.injection.module.NetworkModule
import com.example.ts4_demo.ui.viewModels.SignInViewModel
import com.example.ts4_demo.ui.viewModels.SignUpViewModel
import dagger.Component

@Component(modules = [(NetworkModule::class)])
interface ComponentlInjector {

    /**
     * Injector de dependencias para los viewModels
     */
    fun inject(signInViewModel: SignInViewModel)
    fun inject(signUpViewModel: SignUpViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ComponentlInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}