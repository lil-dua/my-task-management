package dev.ryan.feature.task.tasklist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.ryan.feature.task.tasklist.TaskListRoute
import kotlinx.serialization.Serializable

@Serializable object TaskListRoute


/**
 * Extension function to navigate to ListTask
 */
fun NavController.navigateToListTask(navOptions: NavOptions? = null) {
    this.navigate(route = TaskListRoute, navOptions = navOptions)
}

/**
 * Add ListTask screen to the NavGraph
 */
fun NavGraphBuilder.taskListScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    composable<TaskListRoute> {
        TaskListRoute(
            onNavigateToHome = onNavigateToHome,
            onNavigateToProfile = onNavigateToProfile,
        )
    }
}