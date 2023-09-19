package com.andre.apps.filamentdroid.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andre.apps.filamentdroid.design.FilamentDroidTheme
import com.andre.apps.filamentdroid.ui.first.FirstScreen
import com.andre.apps.filamentdroid.ui.second.SecondScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FilamentDroidTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "first") {
                    composable("first") {
                        FirstScreen {
                            navController.navigate("second")
                        }
                    }
                    composable("second") {
                        SecondScreen()
                    }
                }
            }
        }
    }
}