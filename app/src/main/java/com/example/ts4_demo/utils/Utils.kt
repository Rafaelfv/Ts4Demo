package com.example.ts4_demo.utils

import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.ts4_demo.R
import com.example.ts4_demo.Ts4Application


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

fun EditText.checkForEmpty() {
    if (this.text.isEmpty()) {
        this.setError("Dato necesario")
    }
}

fun EditText.checkForEmpty(message: String) {
    if (this.text.isEmpty()) {
        this.error = message
    }
}

fun String.showToastMessage() {
    Toast.makeText(Ts4Application.context, this, Toast.LENGTH_LONG).show()
}