package com.christian_magana.rickAndMortyMVVM.ui.home

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.christian_magana.rickAndMortyMVVM.data.model.Character
import com.christian_magana.rickAndMortyMVVM.data.remote.RickAndMortyApiClient
import retrofit2.HttpException
import java.lang.Exception

class HomePagingSource(
    private val api: RickAndMortyApiClient,
    private val name: String? = null,
    private val status: String? = null,
    private val gender: String? = null,
) : PagingSource<Int, Character>(){
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try{
            val nextPageNumber = params.key ?: 1
            val response = api.getCharactersPage(nextPageNumber, name, status, gender)

            LoadResult.Page(
                data = response!!.characters,
                prevKey = null,
                nextKey = if (response.characters.isEmpty()) null else nextPageNumber + 1
            )

        }catch (e: Exception){
            Log.d("HOLA","Error Exception", e)
            LoadResult.Error(e)
        }catch (exception: HttpException) {
            Log.d("HOLA","Error HTTP", exception)
            LoadResult.Error(exception)
        }
    }

}