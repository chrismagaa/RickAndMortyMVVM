package com.christian_magana.rickAndMortyMVVM.data.remote

import com.christian_magana.rickAndMortyMVVM.core.RetrofitHelper
import com.christian_magana.rickAndMortyMVVM.data.model.CharactersResponse
import retrofit2.Response
import java.lang.Exception

class RickAndMortyApiClient {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCharactersPage(
        pageIndex: Int?
    ): SimpleResponse<CharactersResponse> {
        return safeApiCall {  retrofit.create(RickAndMortyApiService::class.java).getAllCharacters(pageIndex) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try{
            SimpleResponse.success(apiCall.invoke())
        }catch (e: Exception){
            SimpleResponse.failure(e)
        }
    }
}