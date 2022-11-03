package com.example.ts4_demo.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

interface GeneralInterface {

    fun setTitleScreen(title:String)

    fun addFragment(fragment: Fragment, Tag: String, bundle: Bundle = Bundle())

    fun setFragment(fragment: Fragment, Tag: String)

    fun removeFragment(fragment: Fragment, Tag: String)

    fun onBackPress()

    fun startActivity(activity: AppCompatActivity,finishCurrent : Boolean = false)

}
