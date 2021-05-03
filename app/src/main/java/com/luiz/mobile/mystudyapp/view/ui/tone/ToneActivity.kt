package com.luiz.mobile.mystudyapp.view.ui.tone

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.commons.ext.toast

class ToneActivity : AppCompatActivity() {

    private lateinit var toneDurBar: SeekBar
    private lateinit var toneDurText: TextView

//    private var toneList: ListView
//    private var toneListAdapter: ToneAdapter

    private var toneGenerator: ToneGenerator? = null

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, ToneActivity::class.java).apply { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tone)

        toneGenerator = ToneGenerator(AudioManager.STREAM_SYSTEM, 100)
        toneDurBar = findViewById(R.id.skb_volume_tone)
        toneDurText = findViewById(R.id.tv_tone_duration_text)

        toast(msgText = "FOI MENINÃO!")
    }

}