package com.example.astopiacoffe.feature.ui.welcomeflow.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.astopiacoffe.common.util.Constant.ONBOARDING_SCREEN
import com.example.astopiacoffe.common.util.Constant.SPLASH_SCREEN
import com.example.astopiacoffe.common.util.Constant.WELCOME_NAV_GRAPH
import com.example.astopiacoffe.feature.ui.welcomeflow.screen.splash.SplashScreen


fun NavGraphBuilder.welcomeNavGraph(navController: NavHostController) {

    navigation(
        route = WELCOME_NAV_GRAPH,
        startDestination = SPLASH_SCREEN
    ) {
        composable(route = SPLASH_SCREEN) {
            SplashScreen(onNavigateToOnboarding = {
                    navController.navigate(ONBOARDING_SCREEN){
                        popUpTo(SPLASH_SCREEN){
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = ONBOARDING_SCREEN) {
            SplashScreen(onNavigateToOnboarding = {

            }
            )
        }

    }

}