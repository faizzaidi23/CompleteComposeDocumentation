package com.example.a1_composedocumentationuiarchitecture.ManagingStates_4

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

data class Cityyy(val name:String,val country:String)

val CitySaverr=listSaver<Cityyy,Any>(
    save={listOf(it.name,it.country)},
    restore = {Cityyy(it[0]as String,it[1] as String)}
)

@Composable
fun CityyyScreen(){
    val selectedCity by rememberSaveable(stateSaver = CitySaverr){
        mutableStateOf(Cityyy("Madrid","Spain"))
    }
}



/*
You need listSaver because it's a slightly simpler and more concise way to write a custom saver compared to mapSaver.

The only difference is how the data is stored:

mapSaver stores your object's properties in a Map using string keys (e.g., "Name" -> "Madrid").

listSaver stores them in a List using integer indices (e.g., index 0 -> "Madrid").

You use listSaver when you want to write a custom saver with a little less code and you are confident the order of the properties won't change.
*/





