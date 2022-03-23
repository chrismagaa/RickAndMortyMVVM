package com.christian_magana.rickAndMortyMVVM.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.christian_magana.rickAndMortyMVVM.R
import com.christian_magana.rickAndMortyMVVM.data.model.QueryRAM
import com.christian_magana.rickAndMortyMVVM.databinding.FragmentHomeBinding
import com.christian_magana.rickAndMortyMVVM.ui.MainViewModel
import com.christian_magana.rickAndMortyMVVM.ui.detail.DetailFragment
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    lateinit var adapter: CharacterAdapter
    private var queryRAM = QueryRAM()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        initObservers()
        initListenerSearchView()

        binding.btnFilter.setOnClickListener { findNavController().navigate(R.id.action_navigation_home_to_filterFragment) }
    }

    private fun initListenerSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                queryRAM.name = query?: ""

                mainViewModel.searchCharacters(queryRAM)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
               return true
            }

        })
    }

    private fun initObservers() {
        mainViewModel.characters.observe(viewLifecycleOwner, Observer { characterList ->
            if(characterList != null){
                adapter.submitData(viewLifecycleOwner.lifecycle, characterList)
            }else{
                binding.ivNotWifi.visibility = View.VISIBLE
                Toast.makeText(requireContext(), getString(R.string.unseccssful_network), Toast.LENGTH_SHORT).show()
            }
        })

        mainViewModel.currentQuery.observe(viewLifecycleOwner){queryRAM ->
            if(queryRAM != null){
                this.queryRAM = queryRAM

                binding.chipGroupFilter.removeAllViews()
                if(queryRAM.name.isNotBlank()) addChild("${getString(R.string.txt_name)}: ${queryRAM.name}")
                if(queryRAM.gender.isNotBlank()) addChild("${getString(R.string.txt_gender)}: ${queryRAM.gender}")
                if(queryRAM.status.isNotBlank()) addChild("${getString(R.string.txt_status)}: ${queryRAM.status}")

            }
        }
    }

    private fun addChild(txt: String){
        val chip = Chip(requireContext()).apply {
            text = txt
            setChipBackgroundColorResource(R.color.alternative_color)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.dark))
        }
        binding.chipGroupFilter.addView(chip)
    }

    private fun setupAdapter() {
        adapter = CharacterAdapter{
            val bundle = bundleOf(DetailFragment.ARG_CHARACTER to it)
            findNavController().navigate(R.id.detailFragment, bundle)
        }
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}