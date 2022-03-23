package com.christian_magana.rickAndMortyMVVM.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.christian_magana.rickAndMortyMVVM.R
import com.christian_magana.rickAndMortyMVVM.data.model.Character
import com.christian_magana.rickAndMortyMVVM.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {


    private var _binding: FragmentDetailBinding? = null
     val binding get() = _binding!!

    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            character = it.getParcelable<Character>(ARG_CHARACTER)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.tvId.text = "#${character.id}"
        binding.tvStateValue.text = character.status
        binding.ivCharacter.load(character.image)
        binding.tvName.text = character.name
        binding.tvSpecies.text = character.species
        binding.tvGenderValue.text = character.gender
        binding.tvOrigenValue.text = character.origin.name
        binding.tvLocationValue.text = character.location.name

        binding.ivArrow.setOnClickListener { findNavController().popBackStack(R.id.navigation_home, false) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val ARG_CHARACTER = "arg_charcter"
    }


}