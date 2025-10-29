package dev.ryan.mytaskmanagement.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.ryan.feature.addtask.navigation.addTaskScreen
import dev.ryan.feature.addtask.navigation.navigateToAddTask
import dev.ryan.feature.home.navigation.HomeRoute
import dev.ryan.feature.home.navigation.homeScreen
import dev.ryan.feature.home.navigation.navigateToHome
import dev.ryan.feature.profile.navigation.navigateToProfile
import dev.ryan.feature.profile.navigation.profileScreen

val isLoggedIn = false

@Composable
fun MtmNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Any = HomeRoute
//    startDestination: Any = if (isLoggedIn) HomeRoute else LoginRoute,
) {
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
        profileScreen(
            onNavigateToAddTask = { navController.navigateToAddTask() },
            onNavigateToHome = { navController.navigateToHome() }
        )
    }
}

