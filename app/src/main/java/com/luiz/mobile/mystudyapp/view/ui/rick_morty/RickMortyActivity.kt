package com.luiz.mobile.mystudyapp.view.ui.rick_morty

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.commons.ext.toast
import com.luiz.mobile.mystudyapp.view.BaseActivity
import com.luiz.mobile.mystudyapp.view.adapter.CharacterAdapter
import com.luiz.mobile.mystudyapp.view.viewmodel.rick_morty.RickMortyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RickMortyActivity : BaseActivity() {

    private lateinit var mIvError: ImageView
    private lateinit var mVfItems: ViewFlipper
    private lateinit var mTvMsgError: TextView
    private lateinit var mCircleProgress: AlertDialog

    private lateinit var mRvSpecies: RecyclerView
    private val viewModel: RickMortyViewModel by viewModel()

    companion object {
        private const val MSG_ERROR = 0
        private const val RECYCLER = 1
        fun intent(context: Context): Intent {
            return Intent(context, RickMortyActivity::class.java).apply { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rick_morty)
        mCircleProgress = buildCircleProgress()

        findView()

        mCircleProgress.show()

        viewModel.successLiveData.observe(this, { arrSpecies ->
            mVfItems.displayedChild = RECYCLER
            arrSpecies?.let {
                Log.i("onCreate - RickMortyActivity", it.toString())
                val adapter = CharacterAdapter(it)
                mRvSpecies.adapter = adapter
                mRvSpecies.setHasFixedSize(true)
                mRvSpecies.layoutManager = LinearLayoutManager(this@RickMortyActivity)
                mCircleProgress.hide()
            }
        })
        viewModel.networkErrorLiveData.observe(this, { msgErr ->
            mVfItems.displayedChild = MSG_ERROR
            mIvError.visibility = View.VISIBLE
            mTvMsgError.visibility = View.VISIBLE
            mTvMsgError.text = msgErr
            toast(msgText = msgErr)
            mCircleProgress.hide()
        })
        viewModel.serverErrorLiveData.observe(this, { msgSrvErr ->
            mVfItems.displayedChild = MSG_ERROR
            mIvError.visibility = View.VISIBLE
            mTvMsgError.visibility = View.VISIBLE
            mTvMsgError.text = msgSrvErr
            toast(msgText = msgSrvErr)
            mCircleProgress.hide()
        })
        viewModel.characters()
    }

    private fun findView() {
        mRvSpecies = findViewById(R.id.rv_species)
        mVfItems = findViewById(R.id.vf_items_rick_morty)
        mTvMsgError = findViewById(R.id.tv_msg_except)
        mIvError = findViewById(R.id.iv_except)
    }
}