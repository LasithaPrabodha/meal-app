package com.fanshawe_24w_g7_mealapp.g7_mealapp.util

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.fanshawe_24w_g7_mealapp.g7_mealapp.R

open class LoadingDialog(context: Context) : Dialog(context, R.style.LoadingDialogTheme) {
    private val mContext: Context = context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val inflateView: View =
            inflater.inflate(R.layout.loading_dialog, findViewById(R.id.loading_container))
        setCancelable(false)
        setContentView(inflateView)
    }

}