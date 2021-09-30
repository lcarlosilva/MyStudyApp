package com.luiz.mobile.mystudyapp.view.ui.star_wars

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.view.viewmodel.star_wars.StarWarsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StarWarsActivity : AppCompatActivity() {

    private val viewModel: StarWarsViewModel by viewModel()

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, StarWarsActivity::class.java).apply { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars)

        viewModel.species()

    }
}