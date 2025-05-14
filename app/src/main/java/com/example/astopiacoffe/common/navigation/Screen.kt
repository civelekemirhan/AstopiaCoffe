package com.example.astopiacoffe.common.navigation

import com.example.astopiacoffe.common.util.Constant.DETAIL_SCREEN
import com.example.astopiacoffe.common.util.Constant.MAIN_SCREEN
import com.example.astopiacoffe.common.util.Constant.ONBOARDING_SCREEN
import com.example.astopiacoffe.common.util.Constant.SPLASH_SCREEN

sealed class Screen(val route:String) {

    data object Splash : Screen(SPLASH_SCREEN)
    data object Onboarding : Screen(ONBOARDING_SCREEN)
    data object Main : Screen(MAIN_SCREEN)
    data object Detail : Screen(DETAIL_SCREEN)

}