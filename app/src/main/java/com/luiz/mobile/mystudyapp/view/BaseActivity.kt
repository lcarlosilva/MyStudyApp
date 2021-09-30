package com.luiz.mobile.mystudyapp.view

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.luiz.mobile.mystudyapp.R

open class BaseActivity : AppCompatActivity() {
    fun buildCircleProgress(): AlertDialog {
        val circleProgress = layoutInflater.inflate(R.layout.circle_progress, null)
        val progressDialog = AlertDialog.Builder(circleProgress.context, R.style.TransparentProgressDialog)
        progressDialog.setView(circleProgress)
        return progressDialog.create()
    }
}