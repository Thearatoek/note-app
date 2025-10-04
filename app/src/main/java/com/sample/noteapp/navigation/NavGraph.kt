package com.sample.noteapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sample.noteapp.presentation.dashboard.DashboardScreen
import com.sample.noteapp.presentation.note.AddNewNoteScreen

@Composable
fun AppNavGraph(
    startDestination: String,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        dashBoardScreen(navController = navController)
        addNoteScreen(navController = navController)
    }
}

//profile screen
private fun NavGraphBuilder.dashBoardScreen(
    navController: NavHostController,
) {
    composable(
        NavDirections.DashbordScreen.route,
    ) {
        DashboardScreen(
            navController = navController,
        )
    }
}
// add note screen
private fun NavGraphBuilder.addNoteScreen(
    navController: NavHostController,
) {
    composable(
        NavDirections.AddNoteScreen.route,
    ) {
        AddNewNoteScreen(navController = navController)
    }
}
