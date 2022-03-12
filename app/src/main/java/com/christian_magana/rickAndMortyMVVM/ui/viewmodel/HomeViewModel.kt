package com.christian_magana.rickAndMortyMVVM.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christian_magana.rickAndMortyMVVM.data.RickAndMortyRepository
import com.christian_magana.rickAndMortyMVVM.data.model.CharactersResponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val rickAndMortyRepository = RickAndMortyRepository()

    private val _charactersLiveData = MutableLiveData<CharactersResponse?>()
    val charactersLiveData: MutableLiveData<CharactersResponse?> = _charactersLiveData

    val isLoading= MutableLiveData<Boolean>()

    init {
        getCharacters()
    }

    fun getCharacters(page: Int = 2){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = rickAndMortyRepository.getCharactersList(page)
            isLoading.postValue(false)
            _charactersLiveData.postValue(response)
        }
    }

}