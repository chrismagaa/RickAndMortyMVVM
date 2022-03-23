package com.christian_magana.rickAndMortyMVVM.ui

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.christian_magana.rickAndMortyMVVM.common.Constants
import com.christian_magana.rickAndMortyMVVM.data.RickAndMortyRepository
import com.christian_magana.rickAndMortyMVVM.data.model.QueryRAM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel() {

    val currentQuery = MutableLiveData<QueryRAM>()

    init {
        searchCharacters(QueryRAM())
    }

    val characters = currentQuery.switchMap { query ->
        val name = query.name
        val status = query.status
        val gender = query.gender

        rickAndMortyRepository.getCharacters(name, status, gender).cachedIn(viewModelScope)
    }

    fun searchCharacters(query: QueryRAM) {
        currentQuery.postValue(query)
    }



}

