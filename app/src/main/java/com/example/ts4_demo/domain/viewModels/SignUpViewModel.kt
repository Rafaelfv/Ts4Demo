package com.example.ts4_demo.ui.viewModels

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.ts4_demo.ResponseSignup
import com.example.ts4_demo.data.models.User
import com.example.ts4_demo.domain.repository.ApiLogin
import com.example.ts4_demo.domain.viewModels.BaseViewModel
import com.example.ts4_demo.utils.checkForEmpty
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
        userEmail: EditText?,
        userPass: EditText?,
        userPassConfirm: EditText?
    ) {
        userName?.checkForEmpty()
        userLastName?.checkForEmpty()
        userEmail?.checkForEmpty()
        userPass?.checkForEmpty()
        userPassConfirm?.checkForEmpty()

        //val newUSer = User(userName?.text.toString(),userEmail?.text.toString(),"")
        val user = User(username = "Rafafv", email = "rflores@ts4.mx", password = "Rafa123@", firstName = "Rafael"
        , firstSurname = "Flores", secondSurname = "Velazquez", profile = "promoter")
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