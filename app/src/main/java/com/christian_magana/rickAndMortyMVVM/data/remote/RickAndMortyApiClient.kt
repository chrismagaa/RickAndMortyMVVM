package com.christian_magana.rickAndMortyMVVM.data.remote

import com.christian_magana.rickAndMortyMVVM.data.model.CharactersResponse
import com.christian_magana.rickAndMortyMVVM.data.model.ResponseLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickAndMortyApiClient @Inject constructor(private val api:RickAndMortyApiService){


    suspend fun getCharactersPage(
        pageIndex: Int?,
        name: String? = null,
        status: String? = null,
        gender: String? = null,
    ): CharactersResponse? {
        return withContext(Dispatchers.IO){
            val response =  api.getAllCharacters(pageIndex, name, status, gender)
            response.body()
        }
    }

    suspend fun getLocation(
        id: Int?,
    ): ResponseLocation? {
        return withContext(Dispatchers.IO){
            val response = api.getLocation(id)
            response.body()
        }
    }





}