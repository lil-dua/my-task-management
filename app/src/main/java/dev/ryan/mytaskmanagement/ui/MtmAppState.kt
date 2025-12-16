package dev.ryan.mytaskmanagement.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import dev.ryan.feature.home.navigation.navigateToHome
import dev.ryan.feature.profile.navigation.navigateToProfile
import dev.ryan.feature.task.tasklist.navigation.navigateToListTask
import dev.ryan.mytaskmanagement.navigation.TopLevelDestination


@Composable
fun rememberMtmAppState(
    navController: NavHostController = rememberNavController()
): MtmAppState {
    return remember(navController) {
        MtmAppState(
            navController = navController,
        )
    }

}

@Stable
class MtmAppState(
    val navController: NavHostController
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)
    val currentDestination: NavDestination?
        @Composable get() {
            navController.currentDestination
            // Collect the currentBackStackEntryFlow as a state
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            // Fallback to previousDestination if currentEntry is null
            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
                TopLevelDestination.LIST_TASK -> navController.navigateToListTask(topLevelNavOptions)
                TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelNavOptions)
            }
        }
    }
}