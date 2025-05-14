package com.example.astopiacoffe.feature.ui.welcomeflow.screen.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun OnBoardingScreen(onNavigateToHome: () -> Unit) {

    val pages = listOf(
        OnBoarding.FIRST,
        OnBoarding.SECOND,
        OnBoarding.THIRD
    )

    val pagerState = rememberPagerState {
        pages.size
    }

    var isBtnEnabled by remember {
        mutableStateOf(false)
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OnBoardingItem(page = pages[pagerState.currentPage])
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)) {


            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)) {
                AnimatedVisibility(isBtnEnabled) {


                }

            }

        }

    }


}