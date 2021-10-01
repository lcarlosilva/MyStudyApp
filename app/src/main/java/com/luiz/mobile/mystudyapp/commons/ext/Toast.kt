package com.luiz.mobile.mystudyapp.commons.ext

import android.content.Context
import android.widget.Toast

fun Context.toast(
        context: Context = applicationContext,
        msgText: String,
        duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(context, msgText, duration).show()
}