package com.luiz.mobile.mystudyapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.uncopt.android.widget.text.justify.JustifiedTextView

class MainActivity : AppCompatActivity() {

    private lateinit var mTvMessage: TextView
//    private lateinit var mTvVersionAPP: TextView
    private lateinit var mTvInfosDevice: JustifiedTextView

//    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTvMessage = findViewById(R.id.tv_message)
//        mTvVersionAPP = findViewById(R.id.tv_number_version)
        mTvInfosDevice = findViewById(R.id.jt_infos_hardware)

//        mTvVersionAPP.text = getString(R.string.version_app, BuildConfig.VERSION_NAME)

        saveInfo().let { txInfo ->
            mTvInfosDevice.text = txInfo
        }

        /*viewModel.messageLiveData.observe(this, Observer {
            it?.let {
                Tone().startTone(Tone.TONE_PROP_BEEP_TWO)
                mTvMessage.text = it.text
                toast(message = "This is a TOAST Long", toastDuration = Toast.LENGTH_LONG)
            }
        })
        viewModel.errorLiveData.observe(this, Observer {
            it?.let {
                Tone().startTone(Tone.TONE_EMERGENCY_RING_BACK)
                toast(message = it)
            }
        })
        viewModel.getMessage()*/
    }

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_call_camera, menu)
        return super.onCreateOptionsMenu(menu)
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_call_cam -> {
            toast(
                message = "APONTE A CAMERA PARA UM\n CODIGO DE BARRA OU UM QR CODE",
                toastDuration = Toast.LENGTH_LONG
            )
            this.startActivity(ScanQrBarcodeActivity.intent(this))
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }*/

    private fun saveInfo(): String {
        return "Brand: ${Build.BRAND}\n" +
                "Product: ${Build.PRODUCT}\n" +
                "Hardware: ${Build.HARDWARE}\n" +
                "Device: ${Build.DEVICE}\n" +
                "Manufacture: ${Build.MANUFACTURER}\n" +
                "Model: ${Build.MODEL}\n" +
                "Security Patch: ${Build.VERSION.SECURITY_PATCH}\n" +
                "Release: ${Build.VERSION.RELEASE}\n" +
                "SDK: ${Build.VERSION.SDK_INT}\n" +
                "Display: ${Build.DISPLAY}\n" +
                "Board: ${Build.BOARD}\n" +
                "Code Name: ${Build.VERSION.CODENAME}"
    }
}