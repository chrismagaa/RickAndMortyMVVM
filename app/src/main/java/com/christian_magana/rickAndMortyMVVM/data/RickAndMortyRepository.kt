package com.christian_magana.rickAndMortyMVVM.data

import com.christian_magana.rickAndMortyMVVM.data.model.CharactersResponse
import com.christian_magana.rickAndMortyMVVM.data.remote.RickAndMortyApiClient

class RickAndMortyRepository {
    private val api = RickAndMortyApiClient()

    suspend fun getCharactersList(
        pageIndex: Int?
    ): CharactersResponse? {
            val request = api.getCharactersPage(pageIndex)
            if(request.failed || !request.isSuccessful){
                return null
            }
        return request.body
    }
}