package com.example.ts4_demo.ui.viewModels

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.ts4_demo.ResponseSignup
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
        if (userName?.checkIfEmpty("Dato necesario") == true) return
        if (userLastName?.checkIfEmpty("Dato necesario") == true) return
        if (userSurname?.checkIfEmpty("Dato necesario") == true) return
        if (userUsername?.checkIfEmpty("Dato necesario") == true) return
        if (userEmail?.checkIfEmpty("Dato necesario") == true) return
        if (userPass?.checkIfEmpty("Dato necesario") == true) return
        if (userPassConfirm?.checkIfEmpty("Dato necesario") == true) return
        if (userEmail?.checkIfEmailSyntax() == false) return
        if (userPass?.text.toString() != userPassConfirm?.text.toString()) {
            userPass?.error = "Las contraseñas no coinciden"
            return
        }

       /* val user = User (username = userUsername?.text.toString(), email = userEmail?.text.toString(),
            password = userPass?.text.toString(), firstName = userName?.text.toString(),
            firstSurname = userLastName?.text.toString(), secondSurname = userSurname?.text.toString(), profile = "promoter")
        subscription = api.signup(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribeStart()}
            .doOnTerminate { onTerminate() }
            .subscribe({ it -> onSuccessSigUp(it) },
                { error -> showError(error) })*/
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