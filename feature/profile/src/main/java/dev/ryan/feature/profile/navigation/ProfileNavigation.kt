package dev.ryan.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.ryan.feature.profile.ProfileRoute
import kotlinx.serialization.Serializable

@Serializable object ProfileRoute


/**
 * Extension function to navigate to Profile
 */
fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    this.navigate(route = ProfileRoute, navOptions = navOptions)
}

/**
 * Add Profile screen to the NavGraph
 */
fun NavGraphBuilder.profileScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToAddTask: () -> Unit = {},
) {
    composable<ProfileRoute> {
        ProfileRoute(
            onNavigateToHome = onNavigateToHome,
            onNavigateToAddTask = onNavigateToAddTask,
        )
    }
}