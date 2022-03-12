package com.christian_magana.rickAndMortyMVVM.common

import com.christian_magana.rickAndMortyMVVM.R

object Util {

    fun getGenerDrawable(genered: String): Int {
        return when(genered){
            "Male" -> R.drawable.ic_male
            "Female" -> R.drawable.ic_female
            "Genderless" -> R.drawable.ic_genderless
            else -> R.drawable.ic_unknown
        }
    }

    fun getStatusDrawable(genered: String): Int {
        return when(genered){
            "Alive" -> R.drawable.ic_alive
            "Dead" -> R.drawable.ic_dead
            else -> R.drawable.ic_unknown
        }
    }


}