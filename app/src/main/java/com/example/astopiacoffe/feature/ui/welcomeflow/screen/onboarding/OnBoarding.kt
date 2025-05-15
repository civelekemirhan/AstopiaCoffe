package com.example.astopiacoffe.feature.ui.welcomeflow.screen.onboarding

import com.example.astopiacoffe.R

sealed class OnBoarding(val title: Int, val subTitle: Int, val description: Int) {
    data object First : OnBoarding(
        R.string.onboarding_title_1,
        R.string.onboarding_subtitle_1,
        R.string.onboarding_description_1
    )

    data object Second : OnBoarding(
        R.string.onboarding_title_2,
        R.string.onboarding_subtitle_2,
        R.string.onboarding_description_2
    )

    data object Third : OnBoarding(
        R.string.onboarding_title_3,
        R.string.onboarding_subtitle_3,
        R.string.onboarding_description_3
    )
}