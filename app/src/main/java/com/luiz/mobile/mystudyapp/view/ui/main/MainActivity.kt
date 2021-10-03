package com.luiz.mobile.mystudyapp.view.ui.main

import android.os.Bundle
import android.widget.TextView
import com.getbase.floatingactionbutton.FloatingActionButton
import com.luiz.mobile.mystudyapp.BuildConfig
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.view.BaseActivity
import com.luiz.mobile.mystudyapp.view.ui.contact.ContactListsActivity
import com.luiz.mobile.mystudyapp.view.ui.rick_morty.RickMortyActivity
import com.luiz.mobile.mystudyapp.view.ui.tone.ToneActivity
import com.luiz.mobile.mystudyapp.view.viewmodel.main.MainViewModel
import com.uncopt.android.widget.text.justify.JustifiedTextView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var mTvMessage: TextView
    private lateinit var mTvVersionAPP: TextView
    private lateinit var mTvInfosDevice: JustifiedTextView
    private lateinit var mFloatActBtnTone: FloatingActionButton
    private lateinit var mFloatActBtnRickMorty: FloatingActionButton
    private lateinit var mFloatActBtnContacts: FloatingActionButton

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
        mFloatActBtnRickMorty.setOnClickListener {
            startActivity(RickMortyActivity.intent(this))
        }
        mFloatActBtnContacts.setOnClickListener {
            startActivity(ContactListsActivity.intent(this))
        }
    }

    private fun configElementsActy() {
        mTvMessage = findViewById(R.id.tv_message)
        mTvVersionAPP = findViewById(R.id.tv_number_version)
        mTvInfosDevice = findViewById(R.id.jt_infos_hardware)
        mFloatActBtnTone = findViewById(R.id.fab_listen_tone)
        mFloatActBtnRickMorty = findViewById(R.id.fab_rick_morty)
        mFloatActBtnContacts = findViewById(R.id.fab_contacts)
    }

    private fun requestDeviceSpecs() {
        viewModel.infosLiveData.observe(this, { txInfoDevices ->
            mTvInfosDevice.text = txInfoDevices
        })
        viewModel.getInfosDevices()
    }
}