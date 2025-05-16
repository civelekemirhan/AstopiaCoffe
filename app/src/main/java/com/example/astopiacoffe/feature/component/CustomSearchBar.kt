package com.example.astopiacoffe.feature.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomSearchBar(value:String,onValueChange:(String)->Unit){

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(0.9f).height(50.dp),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(text = "Kahve ismi giriniz...") },
    )


}