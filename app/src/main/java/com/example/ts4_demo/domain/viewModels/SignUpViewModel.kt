package com.example.ts4_demo.ui.viewModels

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.ts4_demo.ResponseSignup
import com.example.ts4_demo.Ts4Application
import com.example.ts4_demo.data.models.User
import com.example.ts4_demo.domain.repository.ApiLogin
import com.example.ts4_demo.domain.viewModels.BaseViewModel
import com.example.ts4_demo.utils.checkIfEmailSyntax
import com.example.ts4_demo.utils.checkIfEmpty
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class SignUpViewModel: BaseViewModel() {


    @Inject
    lateinit var api: ApiLogin
    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    var codeHttp: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    fun signup(
        userName: EditText?,
        userLastName: EditText?,
        userSurname: EditText?,
        userUsername: EditText?,
        userEmail: EditText?,
        userPass: EditText?,
        userPassConfirm: EditText?
    ) {
        if (userName?.checkIfEmpty(Ts4Application.resourceManager.getEmptyMessage) == true) return
        if (userLastName?.checkIfEmpty(Ts4Application.resourceManager.getEmptyMessage) == true) return
        if (userSurname?.checkIfEmpty(Ts4Application.resourceManager.getEmptyMessage) == true) return
        if (userUsername?.checkIfEmpty(Ts4Application.resourceManager.getEmptyMessage) == true) return
        if (userEmail?.checkIfEmpty(Ts4Application.resourceManager.getEmptyMessage) == true) return
        if (userPass?.checkIfEmpty(Ts4Application.resourceManager.getEmptyMessage) == true) return
        if (userPassConfirm?.checkIfEmpty(Ts4Application.resourceManager.getEmptyMessage) == true) return
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail?.text.toString()).matches()) return
        if (userPass?.text.toString() != userPassConfirm?.text.toString()) {
            userPass?.error = Ts4Application.resourceManager.getPasswordNotEquals
            return
        }

        val user = User (username = userUsername?.text.toString(), email = userEmail?.text.toString(),
            password = userPass?.text.toString(), firstName = userName?.text.toString(),
            firstSurname = userLastName?.text.toString(), secondSurname = userSurname?.text.toString(), profile = "promoter")
        subscription = api.signup(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribeStart()}
            .doOnTerminate { onTerminate() }
            .subscribe({ it -> onSuccessSigUp(it) },
                { error -> showError(error) })
    }

    private fun showError(error: Throwable?) {
        error?.printStackTrace()
    }

    private fun onSuccessSigUp(response: Response<ResponseSignup>?) {

        when (response?.code()) {
            201 -> {
                codeHttp.value = 201
            }
            400 -> codeHttp.value = 400
            401 -> codeHttp.value = 401
            500 -> codeHttp.value = 500
            200 ->{
                codeHttp.value = 200
            }
        }
    }

    private fun onTerminate() {
        loadingVisibility.value = View.GONE
    }

    private fun onSubscribeStart() {
        loadingVisibility.value = View.VISIBLE
    }

}