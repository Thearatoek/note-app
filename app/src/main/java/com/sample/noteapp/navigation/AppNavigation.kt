package com.sample.noteapp.navigation


sealed class NavDirections(val route: String) {

    object DashbordScreen : NavDirections("dashboard_screen")

    object SplashScreen : NavDirections("splash_screen")

    object AddNoteScreen : NavDirections("add_note_screen/{noteId}") {
        fun createRoute(noteId: Int) = "add_note_screen/$noteId"
    }

}