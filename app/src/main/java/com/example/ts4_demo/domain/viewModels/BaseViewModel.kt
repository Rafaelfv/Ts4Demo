package com.example.ts4_demo.domain.viewModels

import androidx.lifecycle.ViewModel
import com.example.ts4_demo.Ts4Application
import com.example.ts4_demo.ui.viewModels.SignInViewModel
import com.example.ts4_demo.ui.viewModels.SignUpViewModel

abstract class BaseViewModel: ViewModel() {

    init {
        inject()
    }

    private fun inject() {
        when(this){
            is SignInViewModel -> Ts4Application.component.inject(this)
            is SignUpViewModel -> Ts4Application.component.inject(this)
        }
    }
}