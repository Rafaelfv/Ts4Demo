package com.example.ts4_demo.ui.viewModels

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.ts4_demo.Ts4Application
import com.example.ts4_demo.data.models.login.Credentials
import com.example.ts4_demo.data.models.login.LoginResponse
import com.example.ts4_demo.domain.repository.ApiLogin
import com.example.ts4_demo.domain.viewModels.BaseViewModel
import com.example.ts4_demo.utils.checkForEmpty
import com.example.ts4_demo.utils.showToastMessage
import com.grupo.jibaro.tienditas_repartidor.utils.MySharePreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class SignInViewModel : BaseViewModel() {

    @Inject
    lateinit var api: ApiLogin
    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var isLogin: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var subscription: Disposable

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

    private fun onSuccessSigUp(response: Response<LoginResponse>?) {

        when (response?.code()) {
            200 -> {
                isLogin.value = true
                MySharePreferences.setLogin(true)
                MySharePreferences.setIdToken(response.body()!!.idToken)
                MySharePreferences.setRefreshToken(response.body()!!.refreshToken)
                MySharePreferences.setSalesUserId(response.body()!!.salesforceUserId)
                MySharePreferences.setSalesforceToken(response.body()!!.salesforceIdToken)

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