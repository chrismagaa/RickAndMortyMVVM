package com.christian_magana.rickAndMortyMVVM.ui.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.christian_magana.rickAndMortyMVVM.common.Util.getGenerDrawable
import com.christian_magana.rickAndMortyMVVM.common.Util.getStatusDrawable
import com.christian_magana.rickAndMortyMVVM.data.model.Character
import com.christian_magana.rickAndMortyMVVM.databinding.ActivityDetailBinding

class DetailActivity :  AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<Character>(EXTRA_CHARACTER)?.let{character ->
         Glide.with(this).load(character.image).into(binding.ivCharacter)
            binding.ivGenered.setImageResource(getGenerDrawable(character.gender))
            binding.ivStatus.setImageResource(getStatusDrawable(character.status))
            binding.tvName.text = character.name
            binding.tvSpecies.text = character.species
        }
    }

    fun backActivity(view: View) {
        onBackPressed()
    }

    companion object {
        const val EXTRA_CHARACTER = "EXTRA_CHARACTER"

        fun startActivity(activity: Activity, character: Character) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(EXTRA_CHARACTER, character)
                activity.startActivity(intent)
        }
    }


}