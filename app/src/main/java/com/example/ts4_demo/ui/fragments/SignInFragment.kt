package com.example.ts4_demo.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ts4_demo.R
import com.example.ts4_demo.utils.FRAGMENT_TAG_SIGNUP
import com.example.ts4_demo.utils.addFragmentExtension
import kotlinx.android.synthetic.main.fragment_signin.*


class SignInFragment: Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_go_signup.setOnClickListener {
            addFragmentExtension(
                fragmentManager = requireActivity().supportFragmentManager,
                fragment = SignUpFragment(), tag = FRAGMENT_TAG_SIGNUP, idContent = R.id.content_login
            )
        }
    }

}