package com.example.astopiacoffe.feature.ui.welcomeflow.screen.splash

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.astopiacoffe.R
import com.example.astopiacoffe.common.util.Constant.MAIN_NAV_GRAPH
import com.example.astopiacoffe.common.util.Constant.ONBOARDING_SCREEN
import com.example.astopiacoffe.common.util.Constant.SPLASH_SCREEN_DURATION
import com.example.astopiacoffe.feature.ui.welcomeflow.screen.WelcomeViewModel
import com.example.astopiacoffe.ui.theme.appBackground
import com.example.astopiacoffe.ui.theme.reverseTextColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigate: (targetDestination: String) -> Unit) {

    val viewModel = hiltViewModel<WelcomeViewModel>()

    val isOnBoardingCompleted = viewModel.isOnBoardingCompleted.collectAsState().value

    var circularIndicatorVisibility by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = isOnBoardingCompleted) {
        delay(SPLASH_SCREEN_DURATION)
        circularIndicatorVisibility = false
        if(isOnBoardingCompleted){
            onNavigate(MAIN_NAV_GRAPH)
        }else{
            onNavigate(ONBOARDING_SCREEN)
        }
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.appBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.coffee_app_icon),
                contentDescription = "App Icon"
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                stringResource(R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.reverseTextColor
            )
            Spacer(modifier = Modifier.height(40.dp))
            AnimatedVisibility(circularIndicatorVisibility) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.reverseTextColor)
            }

        }
    }


}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SplashScreenPreview() {
    SplashScreen {

    }
}