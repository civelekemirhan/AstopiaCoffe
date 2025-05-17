package com.example.astopiacoffe.feature.ui.mainflow.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.astopiacoffe.common.util.Constant.DETAIL_SCREEN
import com.example.astopiacoffe.common.util.Constant.MAIN_NAV_GRAPH
import com.example.astopiacoffe.common.util.Constant.MAIN_SCREEN
import com.example.astopiacoffe.feature.ui.mainflow.screen.detail.DetailScreen
import com.example.astopiacoffe.feature.ui.mainflow.screen.main.MainScreen
import com.example.astopiacoffe.feature.ui.mainflow.screen.MainViewModel
import com.example.astopiacoffe.ui.theme.appBackground


fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {

    navigation(
        route = MAIN_NAV_GRAPH,
        startDestination = MAIN_SCREEN
    ) {

        composable(route = MAIN_SCREEN) { navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(MAIN_NAV_GRAPH)
            }
            val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
            MainScreen(
                viewModel = mainViewModel,
                onNavigateToDetail = { navController.navigate(DETAIL_SCREEN) },
                handleSystemColor = { systemUiController ->
                    val theme = isSystemInDarkTheme()

                    systemUiController.setStatusBarColor(
                        color = MaterialTheme.colorScheme.appBackground,
                        darkIcons = !theme
                    )
                    systemUiController.setNavigationBarColor(
                        color = MaterialTheme.colorScheme.appBackground,
                        darkIcons = !theme
                    )
                }
            )
        }

        composable(route = DETAIL_SCREEN) { navBackStackEntry ->
            val parentEntry = remember(navBackStackEntry) {
                navController.getBackStackEntry(MAIN_NAV_GRAPH)
            }
            val mainViewModel = hiltViewModel<MainViewModel>(parentEntry)
            DetailScreen(viewModel = mainViewModel,
                onNavigateToHome = {
                    navController.popBackStack()
                },
                handleSystemColor = { systemUiController ->
                    val theme = isSystemInDarkTheme()

                    systemUiController.setStatusBarColor(
                        color = Color.Transparent,
                        darkIcons = false
                    )
                    systemUiController.setNavigationBarColor(
                        color = MaterialTheme.colorScheme.appBackground,
                        darkIcons = !theme
                    )
                })
        }
    }

}
