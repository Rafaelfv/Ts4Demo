package com.example.ts4_demo.ui.viewModels

import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.example.ts4_demo.ResponseBase
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

        val newUSer = User(userName?.text.toString(),userEmail?.text.toString(),"")

        subscription = api.signup(newUSer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribeStart()}
            .doOnTerminate { onTerminate() }
            .subscribe({ it -> onSuccessSigUp(it) },
                { error -> showError(error) })
    }

    private fun showError(error: Throwable?) {

    }

    private fun onSuccessSigUp(response: Response<ResponseBase>?) {
        when (response?.code()) {
            202 -> {
                codeHttp.value = 202
            }
            403 -> codeHttp.value = 403
            404 -> codeHttp.value = 404
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