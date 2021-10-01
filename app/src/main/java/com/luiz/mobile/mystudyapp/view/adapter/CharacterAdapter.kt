package com.luiz.mobile.mystudyapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luiz.mobile.mystudyapp.R
import com.luiz.mobile.mystudyapp.domain.entity.Character
import com.luiz.mobile.mystudyapp.view.BaseActivity
import com.luiz.mobile.mystudyapp.view.ui.rick_morty.detail.CharacterDetailFragment
import kotlinx.android.synthetic.main.item_list_character.view.*

private const val DETAIL_FRAGMENT = "Detail_Fragment"

class CharacterAdapter(private val list: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflateItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_character, parent, false)
        return CharacterViewHolder(inflateItemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bindView(list[position])

    override fun getItemCount(): Int = list.count()

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameCharacter = itemView.tv_name_character
        private val speciesCharacter = itemView.tv_species_character
        private val genderCharacter = itemView.tv_gender_character

        fun bindView(character: Character) {

            val baseActivity = itemView.context as? BaseActivity
            val resources = this.itemView.resources

            nameCharacter.text = resources.getString(R.string.lbl_character_name, character.name)
            speciesCharacter.text = resources.getString(R.string.lbl_character_specie, character.species)
            genderCharacter.text = resources.getString(R.string.lbl_character_gender, character.gender)

            itemView.setOnClickListener {
                val fragment = CharacterDetailFragment(character)
                fragment.show(baseActivity!!.supportFragmentManager, DETAIL_FRAGMENT)
            }
        }
    }
}