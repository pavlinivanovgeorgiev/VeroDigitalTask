package com.example.tasks.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun TasksNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "tasks"
    ) {
        composable("tasks") {
            TasksScreen()
        }
    }
}
