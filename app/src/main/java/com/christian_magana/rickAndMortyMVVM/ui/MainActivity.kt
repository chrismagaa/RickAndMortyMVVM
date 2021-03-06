package com.christian_magana.rickAndMortyMVVM.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.christian_magana.rickAndMortyMVVM.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RickAndMortyMVVM)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}