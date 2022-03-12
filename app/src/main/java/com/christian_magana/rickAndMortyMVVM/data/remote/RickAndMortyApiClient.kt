package com.christian_magana.rickAndMortyMVVM.data.remote

import com.christian_magana.rickAndMortyMVVM.core.RetrofitHelper
import com.christian_magana.rickAndMortyMVVM.data.model.CharactersResponse
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RickAndMortyApiClient @Inject constructor(private val api:RickAndMortyApiService){


    suspend fun getCharactersPage(
        pageIndex: Int?
    ): SimpleResponse<CharactersResponse> {
        return safeApiCall { api.getAllCharacters(pageIndex) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try{
            SimpleResponse.success(apiCall.invoke())
        }catch (e: Exception){
            SimpleResponse.failure(e)
        }
    }
}