package com.example.ts4_demo.ui.viewModels

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.ts4_demo.Ts4Application
import com.example.ts4_demo.data.models.Credentials
import com.example.ts4_demo.domain.repository.ApiLogin
import com.example.ts4_demo.domain.viewModels.BaseViewModel
import com.example.ts4_demo.utils.SUCCESS
import com.example.ts4_demo.utils.showToastMessage
import com.grupo.jibaro.tienditas_repartidor.utils.MySharePreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SignInViewModel : BaseViewModel() {

    @Inject
    lateinit var api: ApiLogin
    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var isLogin: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var subscription: Disposable

    fun signIn(user: EditText?, pass: EditText?) {

        if (user?.text!!.isEmpty()) {
            user.error = Ts4Application.resourceManager.getEmptyMessage
            return
        }

        if (pass?.text!!.isEmpty()) {
            pass.error = Ts4Application.resourceManager.getEmptyMessage
            return
        }

        //  TODO find the email address in Email's Table in Firebase
        //  TODO get the email and send to endpoint to logIn

        val credentials = Credentials("jgrodriguez@ts4.mx", pass.text.toString().trim())

        subscription = api.login(credentials)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingVisibility.value = View.VISIBLE }
            .doOnTerminate { loadingVisibility.value = View.GONE }
            .subscribe(
                { it ->
                    if (it.code() == SUCCESS) {
                        isLogin.value = true
                        MySharePreferences.setLogin(true)
                    } else {
                        Ts4Application.resourceManager.getErrorServer
                    }
                }, { error ->
                    showError(error)
                }
            )
    }

    private fun showError(error: Throwable?) {
        error?.message?.showToastMessage()
    }
}