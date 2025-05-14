package com.example.astopiacoffe.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.astopiacoffe.common.util.Constant.WELCOME_NAV_GRAPH
import com.example.astopiacoffe.feature.ui.mainflow.navigation.mainNavGraph
import com.example.astopiacoffe.feature.ui.welcomeflow.navigation.welcomeNavGraph

@Composable
fun SetUpNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = WELCOME_NAV_GRAPH) {

        welcomeNavGraph(navController)

        mainNavGraph(navController)

    }

}