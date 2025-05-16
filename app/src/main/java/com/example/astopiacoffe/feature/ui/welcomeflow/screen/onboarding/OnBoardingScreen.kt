package com.example.astopiacoffe.feature.ui.welcomeflow.screen.onboarding

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.astopiacoffe.feature.component.CustomAppButton
import com.example.astopiacoffe.feature.ui.welcomeflow.screen.WelcomeViewModel
import com.example.astopiacoffe.ui.theme.appBackground
import com.example.astopiacoffe.ui.theme.appSelectedIndicatorColor
import com.example.astopiacoffe.ui.theme.appUnSelectedIndicatorColor
import com.example.astopiacoffe.ui.theme.reverseTextColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun OnBoardingScreen(onNavigateToMain: () -> Unit) {

    val systemUiController = rememberSystemUiController()
    val theme = isSystemInDarkTheme()

    systemUiController.setStatusBarColor(
        color = MaterialTheme.colorScheme.appBackground,
        darkIcons = !theme
    )
    systemUiController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.appBackground,
        darkIcons = !theme
    )

    val viewModel = hiltViewModel<WelcomeViewModel>()

    val pages = listOf(
        OnBoarding.First, OnBoarding.Second, OnBoarding.Third
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
                .background(MaterialTheme.colorScheme.appBackground)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(end = 10.dp)
                    .weight(0.1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(!isBtnEnabled) {
                    TextButton({
                        viewModel.onCompleted(true)
                        onNavigateToMain()
                    }) {
                        Text("Skip->", fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.reverseTextColor)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalPager(pagerState) {
                    OnBoardingItem(page = pages[it])
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.1f),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center
            ) {

                HorizontalIndicator(pagerState = pagerState) { isEnabled->
                    isBtnEnabled=isEnabled
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                AnimatedVisibility(isBtnEnabled,) {
                    CustomAppButton {
                        viewModel.onCompleted(true)
                        onNavigateToMain()
                    }
                }

            }

        }

    }


}

@Composable
private fun HorizontalIndicator(pagerState: PagerState, setButtonEnabled: (Boolean) -> Unit) {
    repeat(pagerState.pageCount) { iteration ->
        val color =
            if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.appSelectedIndicatorColor else MaterialTheme.colorScheme.appUnSelectedIndicatorColor/* TODO  UI Theme moda göre renkler ayarlanmalı */
        Box(
            modifier = Modifier
                .padding(2.dp)
                .background(color, CircleShape)
                .size(25.dp),
        )
        if (iteration != pagerState.pageCount - 1) {
            Spacer(modifier = Modifier.padding(end = 24.dp))
        }
        LaunchedEffect(pagerState.currentPage) {
            setButtonEnabled(pagerState.currentPage == pagerState.pageCount - 1)
        }
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewOnBoardingScreen(){
    OnBoardingScreen {

    }
}