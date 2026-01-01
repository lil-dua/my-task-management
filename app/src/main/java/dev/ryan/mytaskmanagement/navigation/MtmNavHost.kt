package dev.ryan.mytaskmanagement.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import dev.ryan.feature.task.add.navigation.addTaskScreen
import dev.ryan.feature.task.add.navigation.navigateToAddTask
import dev.ryan.feature.home.navigation.HomeBaseRoute
import dev.ryan.feature.home.navigation.HomeRoute
import dev.ryan.feature.home.navigation.homeScreen
import dev.ryan.feature.home.navigation.navigateToHome
import dev.ryan.feature.profile.navigation.ProfileRoute
import dev.ryan.feature.profile.navigation.navigateToProfile
import dev.ryan.feature.profile.navigation.profileScreen
import dev.ryan.feature.task.tasklist.navigation.TaskListRoute
import dev.ryan.feature.task.tasklist.navigation.taskListScreen
import dev.ryan.mytaskmanagement.ui.MtmAppState
import dev.ryan.core.designsystem.R as DesignR

val isLoggedIn = false

@Composable
fun MtmNavHost(
    appState: MtmAppState,
    startDestination: Any = HomeBaseRoute
//    startDestination: Any = if (isLoggedIn) HomeRoute else LoginRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
//            loginScreen(
//                onLoginSuccess = {
//                    navController.navigateToHome(
//                        navOptions {
//                            popUpTo(LoginRoute) { inclusive = true }
//                        }
//                    )
//                }
//            )

        homeScreen(
            onNavigateToAddTask = { navController.navigateToAddTask() },
            onNavigateToProfile = { navController.navigateToProfile() }
        )

        addTaskScreen(
            onNavigateToHome = { navController.navigateToHome() },
            onNavigateToProfile = { navController.navigateToProfile() }
        )
        taskListScreen (
            onNavigateToHome = { navController.navigateToHome() },
            onNavigateToProfile = { navController.navigateToProfile() }
        )
        profileScreen(
            onNavigateToAddTask = { navController.navigateToAddTask() },
            onNavigateToHome = { navController.navigateToHome() }
        )
    }
}

data class FabConfig(
    val visible: Boolean,
    val iconRes: Int,
    val onClick: () -> Unit
)

fun NavDestination?.fabConfig(
    appState: MtmAppState
): FabConfig? {
    return when {
        hasRoute<HomeRoute>() || hasRoute<TaskListRoute>() -> {
            FabConfig(
                visible = true,
                iconRes = DesignR.drawable.ic_add_task,
                onClick = { appState.navController.navigateToAddTask() }
            )
        }

        hasRoute<ProfileRoute>() -> {
            FabConfig(
                visible = true,
                iconRes = DesignR.drawable.ic_profile,
                onClick = {
                    // TODO: navigate to EditProfile
                }
            )
        }

        else -> null // AddTask, Login, ...
    }
}

inline fun <reified T : Any> NavDestination?.hasRoute(): Boolean {
    val expectedRoute = T::class.qualifiedName
    return this?.route == expectedRoute
}

