package com.example.ts4_demo.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ts4_demo.R


fun setFragmentExtension(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    id: Int,
    tag: String
) {
    fragmentManager.beginTransaction()
        .replace(id, fragment, tag)
        .commit()
}

fun addFragmentExtension(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    idContent: Int,
    tag: String
) {
    fragmentManager.beginTransaction()
        .setCustomAnimations(
            R.animator.enter_anim,
            R.animator.exit_anim,
            R.animator.enter_anim,
            R.animator.exit_anim
        )
        .add(idContent, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

fun removeFragmentExtension(fragmentManager: FragmentManager, fragment: Fragment) {
    fragmentManager.beginTransaction()
        .remove(fragment)
        .commitAllowingStateLoss()
}