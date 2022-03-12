package com.christian_magana.rickAndMortyMVVM.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.christian_magana.rickAndMortyMVVM.R
import com.christian_magana.rickAndMortyMVVM.common.showToast
import com.christian_magana.rickAndMortyMVVM.data.model.Character
import com.christian_magana.rickAndMortyMVVM.databinding.FragmentHomeBinding
import com.christian_magana.rickAndMortyMVVM.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    private var characters = listOf<Character>()
    lateinit var adapter: CharacterAdapter

    private var page by Delegates.notNull<Int>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initRecyclerView()
        initObservers()

        return binding.root
    }

    private fun initObservers() {
        homeViewModel.charactersLiveData.observe(viewLifecycleOwner, Observer { characterList ->
            if(characterList != null){
                characters = characterList.characters
                setCharacters()
            }else{
                binding.ivNotWifi.visibility = View.VISIBLE
                showToast(getString(R.string.unseccssful_network))
            }
        })

        homeViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })
    }

    private fun initRecyclerView() {
        adapter = CharacterAdapter(requireActivity(), characters)
        binding.rvCharacters.adapter = adapter
        binding.rvCharacters.layoutManager = GridLayoutManager(requireContext(), 2)
    }



    private fun setCharacters() {
        adapter.setData(characters)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}