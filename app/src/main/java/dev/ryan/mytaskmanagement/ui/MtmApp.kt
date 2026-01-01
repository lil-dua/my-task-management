package dev.ryan.mytaskmanagement.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import dev.ryan.core.designsystem.component.MtmBackground
import dev.ryan.core.designsystem.component.MtmNavigationSuiteScaffold
import dev.ryan.mytaskmanagement.navigation.MtmNavHost
import dev.ryan.mytaskmanagement.navigation.fabConfig
import kotlin.reflect.KClass

@Composable
fun MtmApp(
    appState: MtmAppState,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
) {
    MtmBackground(modifier = modifier) {
        MainScreen(
            appState = appState,
            modifier = modifier,
            windowAdaptiveInfo = windowAdaptiveInfo
        )
    }
}

@Composable
private fun MainScreen(
    appState: MtmAppState,
    modifier: Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo
) {
    val currentDestination = appState.currentDestination
    val fabConfig = currentDestination.fabConfig(appState)

    MtmNavigationSuiteScaffold(
        navigationSuiteItems = {
            appState.topLevelDestinations.forEach { destination ->
                val selected =
                    currentDestination.isRouteInHierarchy(destination.baseRoute)

                item(
                    selected = selected,
                    onClick = { appState.navigateToTopLevelDestination(destination) },
                    icon = {
                        Icon(
                            painter = painterResource(destination.unselectedIcon),
                            contentDescription = null
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(destination.selectedIcon),
                            contentDescription = null
                        )
                    },
                    label = { Text(stringResource(destination.iconTextId)) }
                )
            }
        },
        windowAdaptiveInfo = windowAdaptiveInfo
    ) {
        Scaffold(
            modifier = modifier,
            containerColor = Color.Transparent,
            floatingActionButton = {
                fabConfig?.takeIf { it.visible }?.let {
                    FloatingActionButton(onClick = it.onClick) {
                        Icon(
                            painter = painterResource(it.iconRes),
                            contentDescription = null
                        )
                    }
                }
            },
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { paddingValues ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)
                    )
            ) {
                Box {
                    MtmNavHost(appState = appState)
                }
            }
        }
    }
}


private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false