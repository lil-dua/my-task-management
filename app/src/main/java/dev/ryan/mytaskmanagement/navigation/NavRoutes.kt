package dev.ryan.mytaskmanagement.navigation

sealed class NavRoutes(val route: String) {
    object Login : NavRoutes("login")
    object Home : NavRoutes("home")
    object AddTask : NavRoutes("add_task")
    object Profile : NavRoutes("profile")
}