package dev.ryan.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.ryan.feature.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable object HomeRoute // route for Home screen
@Serializable object HomeBaseRoute // route for Home screen

/**
 * Extension function to navigate to Home
 */
fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = HomeRoute, navOptions = navOptions)
}

/**
 * Add Home screen to the NavGraph
 */
fun NavGraphBuilder.homeScreen(
    onNavigateToAddTask: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    navigation<HomeBaseRoute>(startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeRoute(
                onNavigateToAddTask = onNavigateToAddTask,
                onNavigateToProfile = onNavigateToProfile,
            )
        }
    }
}