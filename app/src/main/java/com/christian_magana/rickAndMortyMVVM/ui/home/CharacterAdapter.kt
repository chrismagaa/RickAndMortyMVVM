package com.christian_magana.rickAndMortyMVVM.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.christian_magana.rickAndMortyMVVM.R
import com.christian_magana.rickAndMortyMVVM.data.model.Character

class CharacterAdapter(
    private val onClickListener: (Character) -> Unit
) :
    PagingDataAdapter<Character, CharacterViewHolder>(CHARACTER_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.item_character, parent, false)

        return CharacterViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: CharacterViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            viewHolder.render(item, onClickListener)
        }

    }

    companion object {
        val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem
        }
    }
}
