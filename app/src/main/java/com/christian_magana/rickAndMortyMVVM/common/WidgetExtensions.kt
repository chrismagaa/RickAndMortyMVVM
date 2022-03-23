package com.christian_magana.rickAndMortyMVVM.common

import android.widget.RadioButton
import android.widget.RadioGroup
import com.christian_magana.rickAndMortyMVVM.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

fun ChipGroup.getTextChipChecked(): String{
    val selectedId: Int = this.checkedChipId
    return if(selectedId > -1) findViewById<Chip>(selectedId).text.toString() else " "
}

fun ChipGroup.setChipChecked(selectedId: Int){
    if(selectedId > 0) this.findViewById<Chip>(selectedId).isChecked = true
}

fun RadioGroup.getTextButtonChecked(): String {
    val selectedId: Int = this.checkedRadioButtonId
    return if(selectedId > -1) findViewById<RadioButton>(selectedId).text.toString() else " "
}

fun RadioGroup.setButtonChecked(selectedId: Int) {
    if(selectedId > 0) findViewById<RadioButton>(selectedId).isChecked = true
}
