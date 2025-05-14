package com.example.astopiacoffe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.astopiacoffe.common.navigation.SetUpNavGraph
import com.example.astopiacoffe.ui.theme.AstopiaCoffeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AstopiaCoffeTheme {
                SetUpNavGraph(rememberNavController())
            }
        }
    }
}

