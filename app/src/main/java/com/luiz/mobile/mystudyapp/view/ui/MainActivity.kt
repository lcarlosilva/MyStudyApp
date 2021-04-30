package com.luiz.mobile.mystudyapp.view.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.luiz.mobile.mystudyapp.BuildConfig
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.view.viewmodel.MainViewModel
import com.uncopt.android.widget.text.justify.JustifiedTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var mTvMessage: TextView

    private lateinit var mTvVersionAPP: TextView
    private lateinit var mTvInfosDevice: JustifiedTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvMessage = findViewById(R.id.tv_message)
        mTvVersionAPP = findViewById(R.id.tv_number_version)
        mTvInfosDevice = findViewById(R.id.jt_infos_hardware)

        mTvVersionAPP.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)
        mTvMessage.text = getString(R.string.msg_hello_world)

        viewModel.infosLiveData.observe(this, { txInfo ->
            mTvInfosDevice.text = txInfo
        })
        viewModel.getInfosDevices()
    }
}