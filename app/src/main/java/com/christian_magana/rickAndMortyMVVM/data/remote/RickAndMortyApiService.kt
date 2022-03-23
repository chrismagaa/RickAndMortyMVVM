package com.christian_magana.rickAndMortyMVVM.data.remote

import com.christian_magana.rickAndMortyMVVM.data.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("page") page: Int? = 1,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("gender") gender: String? = null,
    ): Response<CharactersResponse>




}