package dev.ryan.feature.addtask.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.ryan.feature.addtask.AddTaskRoute
import kotlinx.serialization.Serializable

@Serializable object AddTaskRoute

/**
 * Extension function to navigate to Add Task
 */
fun NavController.navigateToAddTask(navOptions: NavOptions? = null) {
    this.navigate(route = AddTaskRoute, navOptions = navOptions)
}

/**
 * Add Add Task screen to the NavGraph
 */
fun NavGraphBuilder.addTaskScreen(
    onNavigateToHome: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    composable<AddTaskRoute> {
        AddTaskRoute(
            onNavigateToHome = onNavigateToHome,
            onNavigateToProfile = onNavigateToProfile,
        )
    }
}