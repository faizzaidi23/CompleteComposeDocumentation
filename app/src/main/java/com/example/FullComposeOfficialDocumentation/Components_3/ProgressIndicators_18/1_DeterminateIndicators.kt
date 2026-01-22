package com.example.FullComposeOfficialDocumentation.Components_3.ProgressIndicators_18

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
A determinate indicator reflects how complete an action is
We can use either LinearProgressIndicator or the CircularProgressIndicator composable and pass a value for the progress parameter

*/

@Composable

fun LinearDeterminateIndicator(){
    var currentProgress by remember{mutableFloatStateOf(0f)}
    var loading by remember{ mutableStateOf(false) }
    val scope= rememberCoroutineScope()


    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Button(
            onClick = {
                loading=true
                scope.launch{
                    loadProgress{progress->
                        currentProgress=progress
                    }
                    loading=false

                }
            }, enabled = !loading
        ){
            Text("Start loading")
        }
        if(loading){
            LinearProgressIndicator(
                progress = {currentProgress},
                modifier=Modifier.fillMaxWidth()
            )
        }

    }
}


suspend fun loadProgress(updateProgress:(Float)->Unit){
    for(i in 1..100){
        updateProgress(i.toFloat()/100)
        delay(100)
    }
}