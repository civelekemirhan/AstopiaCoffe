package com.example.astopiacoffe.feature.ui.welcomeflow.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.astopiacoffe.common.util.Constant.MAIN_NAV_GRAPH
import com.example.astopiacoffe.common.util.Constant.ONBOARDING_SCREEN
import com.example.astopiacoffe.common.util.Constant.SPLASH_SCREEN
import com.example.astopiacoffe.common.util.Constant.WELCOME_NAV_GRAPH
import com.example.astopiacoffe.feature.ui.welcomeflow.screen.onboarding.OnBoardingScreen
import com.example.astopiacoffe.feature.ui.welcomeflow.screen.splash.SplashScreen


fun NavGraphBuilder.welcomeNavGraph(navController: NavHostController) {

    navigation(
        route = WELCOME_NAV_GRAPH,
        startDestination = SPLASH_SCREEN
    ) {
        composable(route = SPLASH_SCREEN) {
            SplashScreen{targetDestination->
                    navController.navigate(targetDestination){
                        popUpTo(SPLASH_SCREEN){
                            inclusive = true
                        }
                    }
                }
        }
        composable(route = ONBOARDING_SCREEN) {
            OnBoardingScreen {
                navController.navigate(MAIN_NAV_GRAPH){
                    popUpTo(ONBOARDING_SCREEN){
                        inclusive = true
                    }
                }

            }
        }

    }

}