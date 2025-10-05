package com.sample.noteapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sample.noteapp.presentation.dashboard.DashboardScreen
import com.sample.noteapp.presentation.note.AddNewNoteScreen
import com.sample.noteapp.presentation.splash_screen.SplashScreenView

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
        splashScreen(navController = navController)
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
        arguments = listOf(
            navArgument("noteId") {
                type = NavType.IntType
                defaultValue = -1 // -1 means "add new note"
            }
        )
    ) {
        AddNewNoteScreen(navController = navController)
    }
}
// Splash screen
private fun NavGraphBuilder.splashScreen(
    navController: NavHostController,
) {
    composable(
        NavDirections.SplashScreen.route,
    ) {
        SplashScreenView(navController = navController)
    }
}