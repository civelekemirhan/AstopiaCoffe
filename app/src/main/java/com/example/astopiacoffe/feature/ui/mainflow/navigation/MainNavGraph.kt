package com.example.astopiacoffe.feature.ui.mainflow.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.astopiacoffe.common.util.Constant.DETAIL_SCREEN
import com.example.astopiacoffe.common.util.Constant.MAIN_NAV_GRAPH
import com.example.astopiacoffe.common.util.Constant.MAIN_SCREEN
import com.example.astopiacoffe.feature.ui.mainflow.screen.detail.DetailScreen
import com.example.astopiacoffe.feature.ui.mainflow.screen.main.MainScreen


fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {

    navigation(
        route = MAIN_NAV_GRAPH,
        startDestination = MAIN_SCREEN
    ) {
        composable(route = MAIN_SCREEN) {
             MainScreen {

             }
        }

        composable(route = DETAIL_SCREEN) {
            DetailScreen {

            }
        }

    }

}