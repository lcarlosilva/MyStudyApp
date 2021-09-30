package com.luiz.mobile.mystudyapp.view.ui.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.getbase.floatingactionbutton.FloatingActionButton
import com.luiz.mobile.mystudyapp.BuildConfig
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.view.ui.star_wars.StarWarsActivity
import com.luiz.mobile.mystudyapp.view.ui.tone.ToneActivity
import com.luiz.mobile.mystudyapp.view.viewmodel.main.MainViewModel
import com.uncopt.android.widget.text.justify.JustifiedTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var mTvMessage: TextView
    private lateinit var mTvVersionAPP: TextView
    private lateinit var mTvInfosDevice: JustifiedTextView
    private lateinit var mFloatActBtnTone: FloatingActionButton
    private lateinit var mFloatActBtnStWar: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configElementsActy()

        mTvVersionAPP.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)
        mTvMessage.text = getString(R.string.msg_hello_world)

        requestDeviceSpecs()

        mFloatActBtnTone.setOnClickListener {
            startActivity(ToneActivity.intent(this))
        }
        mFloatActBtnStWar.setOnClickListener {
            startActivity(StarWarsActivity.intent(this))
        }
    }

    private fun configElementsActy() {
        mTvMessage = findViewById(R.id.tv_message)
        mTvVersionAPP = findViewById(R.id.tv_number_version)
        mTvInfosDevice = findViewById(R.id.jt_infos_hardware)
        mFloatActBtnTone = findViewById(R.id.fab_listen_tone)
        mFloatActBtnStWar = findViewById(R.id.fab_star_wars)
    }

    private fun requestDeviceSpecs() {
        viewModel.infosLiveData.observe(this, { txInfoDevices ->
            mTvInfosDevice.text = txInfoDevices
        })
        viewModel.getInfosDevices()
    }
}