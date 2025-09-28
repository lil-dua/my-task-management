package dev.ryan.mytaskmanagement.navigation

import androidx.annotation.StringRes
import dev.ryan.core.designsystem.icon.MtmIcons
import dev.ryan.feature.addtask.navigation.AddTaskRoute
import dev.ryan.feature.home.navigation.HomeBaseRoute
import dev.ryan.feature.home.navigation.HomeRoute
import dev.ryan.feature.profile.navigation.ProfileRoute
import kotlin.reflect.KClass
import dev.ryan.feature.home.R as HomeR
import dev.ryan.feature.addtask.R as AddTaskR
import dev.ryan.feature.profile.R as ProfileR

enum class TopLevelDestination(
    val selectedIcon: Int,
    val unselectedIcon: Int,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route

) {
    HOME(
        selectedIcon = MtmIcons.HomeSelected,
        unselectedIcon = MtmIcons.Home,
        iconTextId = HomeR.string.feature_home_title,
        titleTextId = HomeR.string.feature_home_title,
        route = HomeRoute::class,
        baseRoute = HomeBaseRoute::class
    ),
    ADD_TASK(
        selectedIcon = MtmIcons.AddTask,
        unselectedIcon = MtmIcons.AddTaskSelected,
        iconTextId = AddTaskR.string.feature_addtask_title,
        titleTextId = AddTaskR.string.feature_addtask_title,
        route = AddTaskRoute::class
    ),
    PROFILE(
        selectedIcon = MtmIcons.ProfileSelected,
        unselectedIcon = MtmIcons.Profile,
        iconTextId = ProfileR.string.feature_profile_title,
        titleTextId = ProfileR.string.feature_profile_title,
        route = ProfileRoute::class
    )
}