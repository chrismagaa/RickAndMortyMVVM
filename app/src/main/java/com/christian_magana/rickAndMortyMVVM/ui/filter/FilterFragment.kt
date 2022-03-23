package com.christian_magana.rickAndMortyMVVM.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.christian_magana.rickAndMortyMVVM.R
import com.christian_magana.rickAndMortyMVVM.common.*
import com.christian_magana.rickAndMortyMVVM.data.model.QueryRAM
import com.christian_magana.rickAndMortyMVVM.databinding.FragmentFilterBinding
import com.christian_magana.rickAndMortyMVVM.ui.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment : BottomSheetDialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private var query = QueryRAM()

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.currentQuery.observe(viewLifecycleOwner, Observer{data ->
            if(data!=null){
                query = data
                binding.chipGroupEstado.setChipChecked(query.filters[0])
                binding.rbgGenero.setButtonChecked(query.filters[1])
            }
        })

        binding.btnConfirmar.setOnClickListener {
            query.filters = arrayOf(binding.chipGroupEstado.checkedChipId, binding.rbgGenero.checkedRadioButtonId)

            query.status = binding.chipGroupEstado.getTextChipChecked()
            query.gender = binding.rbgGenero.getTextButtonChecked()

            mainViewModel.searchCharacters(query)

            findNavController().popBackStack(R.id.navigation_home, false)
        }

        binding.btnRestablecer.setOnClickListener {
            query.apply {
                name = ""
                status = ""
                gender = ""
                filters = arrayOf(0,0)
            }
            mainViewModel.searchCharacters(query)
            findNavController().popBackStack(R.id.navigation_home, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}