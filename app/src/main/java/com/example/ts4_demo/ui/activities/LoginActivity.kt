package com.example.ts4_demo.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ts4_demo.R
import com.example.ts4_demo.ui.fragments.SignInFragment
import com.example.ts4_demo.ui.fragments.SignUpFragment
import com.example.ts4_demo.utils.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity: AppCompatActivity(), GeneralInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        setFragment(SignInFragment(), FRAGMENT_TAG_SIGNIN)
    }

    override fun setTitleScreen(title: String) {
    }

    override fun addFragment(fragment: Fragment, Tag: String, bundle: Bundle) {
        addFragmentExtension(
            fragmentManager = supportFragmentManager,
            fragment = fragment, tag = Tag, idContent = R.id.content_login
        )
    }

    override fun setFragment(fragment: Fragment, Tag: String) {
        setFragmentExtension(
            fragmentManager = this.supportFragmentManager,
            fragment = fragment, id = R.id.content_login, tag = Tag
        )
    }

    override fun removeFragment(fragment: Fragment, Tag: String) {
        removeFragmentExtension(
            fragmentManager = supportFragmentManager,
            fragment = fragment
        )
    }

    override fun onBackPress() {
    }

    override fun startActivity(activity: AppCompatActivity, finishCurrent: Boolean) {
        val intent = Intent(this, activity.javaClass)
        startActivity(intent)
        if(finishCurrent){
            this.finish()
        }
    }

}