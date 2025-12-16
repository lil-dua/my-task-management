package dev.ryan.mytaskmanagement.navigation

import androidx.annotation.StringRes
import dev.ryan.core.designsystem.icon.MtmIcons
import dev.ryan.feature.home.navigation.HomeBaseRoute
import dev.ryan.feature.home.navigation.HomeRoute
import dev.ryan.feature.profile.navigation.ProfileRoute
import dev.ryan.feature.task.tasklist.navigation.TaskListRoute
import kotlin.reflect.KClass
import dev.ryan.feature.home.R as homeR
import dev.ryan.feature.profile.R as profileR
import dev.ryan.feature.task.tasklist.R as taskListR

enum class TopLevelDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    HOME(
        selectedIcon = MtmIcons.HomeSelected,
        unselectedIcon = MtmIcons.Home,
        iconTextId = homeR.string.feature_home_title,
        titleTextId = homeR.string.feature_home_title,
        route = HomeRoute::class,
        baseRoute = HomeBaseRoute::class
    ),
    LIST_TASK(
        selectedIcon = MtmIcons.AddTaskSelected,
        unselectedIcon = MtmIcons.AddTask,
        iconTextId = taskListR.string.feature_task_by_date_title,
        titleTextId = taskListR.string.feature_task_by_date_title,
        route = TaskListRoute::class
    ),
    PROFILE(
        selectedIcon = MtmIcons.ProfileSelected,
        unselectedIcon = MtmIcons.Profile,
        iconTextId = profileR.string.feature_profile_title,
        titleTextId = profileR.string.feature_profile_title,
        route = ProfileRoute::class
    )
}