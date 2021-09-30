package com.luiz.mobile.mystudyapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.domain.entity.Species
import kotlinx.android.synthetic.main.item_list_species.view.*


class SpeciesAdapter(val list: List<Species>) :
        RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val inflateItemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_species, parent, false)
        return SpeciesViewHolder(inflateItemView)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) =
            holder.bindView(list[position])

    override fun getItemCount(): Int = list.count()

    class SpeciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameSpecies = itemView.tv_name_species
        private val languageSpecies = itemView.tv_language_species
        private val classificationSpecies = itemView.tv_classification_species
        private val designationSpecies = itemView.tv_designation_species

        fun bindView(species: Species) {
            val resources = this.itemView.resources
            nameSpecies.text = resources.getString(R.string.lbl_name_specie, species.name)
            languageSpecies.text = resources.getString(R.string.lbl_language_specie, species.language)
            classificationSpecies.text = resources.getString(R.string.lbl_classification_specie, species.classification)
            designationSpecies.text = resources.getString(R.string.lbl_designation_specie, species.designation)
        }
    }
}