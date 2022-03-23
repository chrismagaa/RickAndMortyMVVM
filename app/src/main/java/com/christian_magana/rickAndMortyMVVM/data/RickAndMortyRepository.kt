package com.christian_magana.rickAndMortyMVVM.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.christian_magana.rickAndMortyMVVM.common.Constants
import com.christian_magana.rickAndMortyMVVM.data.remote.RickAndMortyApiClient
import com.christian_magana.rickAndMortyMVVM.ui.home.HomePagingSource
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(private val api: RickAndMortyApiClient){

    fun getCharacters(name: String?, status: String?, gender: String?) =
            Pager(
                config = PagingConfig(
                    pageSize = Constants.RAM_API_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { HomePagingSource(api, name, status, gender) }
            ).liveData




}