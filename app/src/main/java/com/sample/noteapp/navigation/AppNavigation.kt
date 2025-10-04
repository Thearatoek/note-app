package com.sample.noteapp.navigation


sealed class NavDirections(val route: String) {

    object DashbordScreen : NavDirections("dashboard_screen")

    object ProfileScreen : NavDirections("profile_screen")

    object AddNoteScreen : NavDirections("add_note_screen")

}