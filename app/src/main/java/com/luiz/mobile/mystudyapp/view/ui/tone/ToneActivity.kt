package com.luiz.mobile.mystudyapp.view.ui.tone

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.commons.ext.toast
import com.luiz.mobile.mystudyapp.commons.utils.Tone
import com.luiz.mobile.mystudyapp.view.adapter.ToneAdapter
import java.util.*

class ToneActivity : AppCompatActivity() {

    private lateinit var toneDurBar: SeekBar
    private lateinit var toneDurText: TextView

    private lateinit var toneList: ListView
    private lateinit var toneListAdapter: ToneAdapter

    private var toneGenerator: ToneGenerator? = null

    private val timer = Timer()
    private val DELAY: Long = 1000

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

        toneDurText.text = getString(R.string.txt_value_ms, this.toneDurBar.progress.toString())
        toneDurBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                toneDurText.text = getString(R.string.txt_value_ms, progress.toString())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        toneList = findViewById(R.id.lt_tonelist)
        toneListAdapter = ToneAdapter(this, R.layout.row, Tone.tones)
        toneList.adapter = toneListAdapter
        toneList.onItemClickListener =
                OnItemClickListener { parent, _, position, _ ->
                    val t = parent.getItemAtPosition(position) as Tone
                    val type = t.toneType
                    val durationMs = toneDurBar!!.progress
                    toneGenerator!!.startTone(type, durationMs)
                }
        val toast = Toast.makeText(baseContext, "MENSAGEM", Toast.LENGTH_LONG)
        timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        toast.show()
                    }
                },
                DELAY
        )
    }
}