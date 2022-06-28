package com.christian_magana.rickAndMortyMVVM.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.christian_magana.rickAndMortyMVVM.data.model.Character
import com.christian_magana.rickAndMortyMVVM.databinding.ItemCharacterBinding

class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemCharacterBinding.bind(view)

    fun render(character: Character, onClickListner: (Character) -> Unit){
        binding.tvName.text = character.name
        binding.ivCharacter.load(character.image){
            transformations(CircleCropTransformation())
        }
        binding.cvCharacter.setOnClickListener {
            onClickListner(character)
        }
    }
}