package com.example.ts4_demo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ts4_demo.R
import com.example.ts4_demo.ui.viewModels.SignUpViewModel
import com.example.ts4_demo.utils.showToastMessage
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment: Fragment() {

    private lateinit var viewModel: SignUpViewModel
    private var showProgress: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_signup.setOnClickListener {
            viewModel.signup(user_name, user_last_name, user_email, user_pass, user_pass_confirm)
        }

        viewModel.codeHttp.observe(viewLifecycleOwner, Observer<Int> {
            when(it){
                202 -> {
                    "Registro exitoso".showToastMessage()
                    //listener.goToCodeFragment(it.body())
                }
                403 -> "un error".showToastMessage()
                404 -> "un error".showToastMessage()
                200 -> {

                }
            }
        })

        viewModel.loadingVisibility
            .observe(viewLifecycleOwner, Observer<Int> {
                when (it) {
                    View.VISIBLE -> {
                      showProgress = true
                    }
                    View.GONE -> {
                        showProgress = false
                    }
                }
            })
    }

}