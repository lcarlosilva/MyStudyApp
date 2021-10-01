package com.luiz.mobile.mystudyapp.view.ui.rick_morty.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.commons.ext.setWidthPercent
import com.luiz.mobile.mystudyapp.domain.entity.Character
import kotlinx.android.synthetic.main.fragment_detail_character.*

class CharacterDetailFragment(private val character: Character) : DialogFragment() {

    private lateinit var mIvProfile: ImageView
    private lateinit var mTvName: TextView
    private lateinit var mTvStatus: TextView
    private lateinit var mTvSpecies: TextView
    private lateinit var mTvGender: TextView
    private lateinit var mTvOrigin: TextView
    private lateinit var mTvLocation: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.fragment_detail_character, container, false)
        configFragment(inflate)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(80)
        btn_ok.setOnClickListener {
            dismiss()
        }
        this.activity?.let {
            val urlImage = character.image
            Glide.with(mIvProfile)
                .load(urlImage)
                .into(mIvProfile)
            mTvName.text = character.name
            mTvStatus.text = getString(R.string.lbl_character_status, character.status)
            mTvSpecies.text = getString(R.string.lbl_character_specie, character.species)
            mTvGender.text = getString(R.string.lbl_character_gender, character.gender)
            mTvOrigin.text = getString(R.string.lbl_character_origin, character.origin.name)
            mTvLocation.text = getString(R.string.lbl_character_location, character.location.name)
        }
    }

    private fun configFragment(inflate: View) {
        mIvProfile = inflate.findViewById(R.id.iv_profile_character)
        mTvName = inflate.findViewById(R.id.tv_detail_name_character)
        mTvStatus = inflate.findViewById(R.id.tv_detail_status_character)
        mTvSpecies = inflate.findViewById(R.id.tv_detail_species_character)
        mTvGender = inflate.findViewById(R.id.tv_detail_gender_character)
        mTvOrigin = inflate.findViewById(R.id.tv_detail_origin_character)
        mTvLocation = inflate.findViewById(R.id.tv_detail_location_character)
    }
}