package com.fanshawe_24w_g7_mealapp.g7_mealapp.util

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment


fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun Fragment.hideKeyboard() = ViewCompat
    .getWindowInsetsController(requireView())
    ?.hide(WindowInsetsCompat.Type.ime())