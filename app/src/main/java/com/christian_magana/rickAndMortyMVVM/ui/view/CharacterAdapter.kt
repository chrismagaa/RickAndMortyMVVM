package com.christian_magana.rickAndMortyMVVM.ui.view

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.christian_magana.rickAndMortyMVVM.R
import com.christian_magana.rickAndMortyMVVM.data.model.Character

class CharacterAdapter(private var activity: Activity, private var characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCharacter: TextView = view.findViewById(R.id.tvName)
        val ivCharacter: ImageView = view.findViewById(R.id.ivCharacter)
        val cvCharacter: CardView = view.findViewById(R.id.cvCharacter)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_character, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = characters[position]
        viewHolder.tvCharacter.text = item.name
        Glide.with(activity)
            .load(item.image)
            .circleCrop()
            .into(viewHolder.ivCharacter)
        viewHolder.cvCharacter.setOnClickListener {
            DetailActivity.startActivity(activity, item)
        }



    }

    fun setData(data: List<Character>){
        characters = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.size

}