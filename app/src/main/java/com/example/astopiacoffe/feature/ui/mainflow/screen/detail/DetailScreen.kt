package com.example.astopiacoffe.feature.ui.mainflow.screen.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.astopiacoffe.feature.ui.mainflow.screen.MainViewModel
import com.example.astopiacoffe.ui.theme.appBackground
import com.example.astopiacoffe.ui.theme.reverseTextColor
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DetailScreen(
    viewModel: MainViewModel,
    onNavigateToHome: () -> Unit,
    handleSystemColor: @Composable (systemUiController: SystemUiController) -> Unit
) {

    handleSystemColor(rememberSystemUiController())

    val coffeeItem by viewModel.passArgumentState.collectAsState()

    Log.d("DetailScreen", "Coffee Item: $coffeeItem")


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.appBackground)

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(coffeeItem.image)
                        .crossfade(true)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        IconButton(onClick = { onNavigateToHome() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back Button",
                                tint = Color.White
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black.copy(alpha = 0.4f))
                            .padding(20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            coffeeItem.title,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp
                        )
                    }

                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
        ) {
            Column {
                Text(coffeeItem.description, color = MaterialTheme.colorScheme.reverseTextColor)

                Column(
                    modifier = Modifier.padding(start = 20.dp, top = 16.dp)
                ) {
                    Text(
                        text = "Ingredients:",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.reverseTextColor,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )


                    coffeeItem.ingredients?.forEach { ingredient ->
                        Text(
                            text = "• ${ingredient ?: ""}",
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = MaterialTheme.colorScheme.reverseTextColor,
                            fontSize = 14.sp
                        )
                    }
                }

            }

        }

    }


}