package com.example.ts4_demo.ui.viewModels

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.ts4_demo.Ts4Application
import com.example.ts4_demo.data.models.login.Credentials
import com.example.ts4_demo.data.models.login.LoginResponse
import com.example.ts4_demo.domain.repository.ApiLogin
import com.example.ts4_demo.domain.repository.ApiSalesForce
import com.example.ts4_demo.domain.viewModels.BaseViewModel
import com.example.ts4_demo.utils.MySharePreferences
import com.example.ts4_demo.utils.checkForEmpty
import com.example.ts4_demo.utils.showToastMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class SignInViewModel : BaseViewModel() {

    @Inject
    lateinit var api: ApiLogin
    @Inject
    lateinit var apiSF:ApiSalesForce


    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var isLogin: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var subscription: Disposable

    fun signIn(user: EditText?, pass: EditText?){
        val a = 0

        subscription = apiSF.getContacts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingVisibility.value = View.VISIBLE }
            .doOnTerminate { loadingVisibility.value = View.GONE }
            .subscribe(
                { it ->
                    val a = 0
                }, { error ->
                    val a = 0
                }
            )
    }
/*
    fun signIn(user: EditText?, pass: EditText?) {

        if (user?.text!!.isEmpty()) {
            user.checkForEmpty(Ts4Application.resourceManager.getEmptyMessage)
            return
        }

        if (pass?.text!!.isEmpty()) {
            pass.checkForEmpty(Ts4Application.resourceManager.getEmptyMessage)
            return
        }

        val credentials = Credentials(user.text.toString().trim(), pass.text.toString().trim())

        subscription = api.login(credentials)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { loadingVisibility.value = View.VISIBLE }
            .doOnTerminate { loadingVisibility.value = View.GONE }
            .subscribe(
                { it ->
                    onSuccessSigUp(it)
                }, { error ->
                    showError(error)
                }
            )
    }

 */

    private fun onSuccessSigUp(response: Response<LoginResponse>?) {

        when (response?.code()) {
            200 -> {
                isLogin.value = true

                MySharePreferences.apply {
                    setLogin(true)
                    setIdToken(response.body()!!.idToken)
                    setRefreshToken(response.body()!!.refreshToken)
                    setSalesUserId(response.body()!!.salesforceUserId)
                    setSalesforceToken(response.body()!!.salesforceIdToken)
                }

                success(Ts4Application.resourceManager.getLoginSuccess)
            }
            400 -> {
                Ts4Application.resourceManager.getNoVerify.showToastMessage()
            }
            else -> {
                Ts4Application.resourceManager.getErrorServer.showToastMessage()
            }
        }
    }

    private fun success(message: String) {
        message?.showToastMessage()
    }

    private fun showError(error: Throwable?) {
        error?.message?.showToastMessage()
    }
}