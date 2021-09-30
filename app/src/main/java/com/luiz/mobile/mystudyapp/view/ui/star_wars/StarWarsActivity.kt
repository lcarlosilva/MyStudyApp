package com.luiz.mobile.mystudyapp.view.ui.star_wars

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.commons.ext.toast
import com.luiz.mobile.mystudyapp.view.BaseActivity
import com.luiz.mobile.mystudyapp.view.adapter.SpeciesAdapter
import com.luiz.mobile.mystudyapp.view.viewmodel.star_wars.StarWarsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarWarsActivity : BaseActivity() {

    private lateinit var mCircleProgress: AlertDialog

    private lateinit var mRvSpecies: RecyclerView
    private val viewModel: StarWarsViewModel by viewModel()

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, StarWarsActivity::class.java).apply { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars)
        mCircleProgress = buildCircleProgress()

        mRvSpecies = findViewById(R.id.rv_species)
        mCircleProgress.show()

        viewModel.successLiveData.observe(this, { arrSpecies ->
            arrSpecies?.let {
                Log.i("onCreate - StarWarsActivity", it.toString())
                val adapter = SpeciesAdapter(it)
                mRvSpecies.adapter = adapter
                mRvSpecies.setHasFixedSize(true)
                mRvSpecies.layoutManager = LinearLayoutManager(this)
                mCircleProgress.hide()
            }
        })
        viewModel.networkErrorLiveData.observe(this, { msgErr ->
            toast(msgText = msgErr)
            mCircleProgress.hide()
        })
        viewModel.serverErrorLiveData.observe(this, { msgSrvErr ->
            toast(msgText = msgSrvErr)
            mCircleProgress.hide()
        })
        viewModel.species()
    }
}