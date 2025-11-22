package dev.ryan.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItemColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.ryan.core.designsystem.icon.MtmIcons
import dev.ryan.core.designsystem.theme.MtmTheme


@Composable
fun RowScope.NiaNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    alwaysShowLabel: Boolean = true,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    label: @Composable (() -> Unit)? = null,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MtmNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MtmNavigationDefaults.navigationContentColor(),
            selectedTextColor = MtmNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MtmNavigationDefaults.navigationContentColor(),
            indicatorColor = MtmNavigationDefaults.navigationIndicatorColor(),
        ),
    )
}

@Composable
fun MtmNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = MtmNavigationDefaults.navigationContentColor(),
        tonalElevation = 0.dp,
        content = content,
    )
}


/**
 * My Task Management navigation suite scaffold with item and content slots.
 * Wraps Material 3 [NavigationSuiteScaffold].
 *
 * @param modifier Modifier to be applied to the navigation suite scaffold.
 * @param navigationSuiteItems A slot to display multiple items via [NiaNavigationSuiteScope].
 * @param windowAdaptiveInfo The window adaptive info.
 * @param content The app content inside the scaffold.
 */
@Composable
fun MtmNavigationSuiteScaffold(
    navigationSuiteItems: MtmNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    windowAdaptiveInfo: WindowAdaptiveInfo = currentWindowAdaptiveInfo(),
    content: @Composable () -> Unit,
) {
    val layoutType = NavigationSuiteScaffoldDefaults
        .calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val navigationSuiteItemColors = NavigationSuiteItemColors(
        navigationBarItemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = MtmNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MtmNavigationDefaults.navigationContentColor(),
            selectedTextColor = MtmNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MtmNavigationDefaults.navigationContentColor(),
            indicatorColor = MtmNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationRailItemColors = NavigationRailItemDefaults.colors(
            selectedIconColor = MtmNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MtmNavigationDefaults.navigationContentColor(),
            selectedTextColor = MtmNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MtmNavigationDefaults.navigationContentColor(),
            indicatorColor = MtmNavigationDefaults.navigationIndicatorColor(),
        ),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(
            selectedIconColor = MtmNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MtmNavigationDefaults.navigationContentColor(),
            selectedTextColor = MtmNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MtmNavigationDefaults.navigationContentColor(),
        ),
    )

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            MtmNavigationSuiteScope(
                navigationSuiteScope = this,
                navigationSuiteItemColors = navigationSuiteItemColors,
            ).run(navigationSuiteItems)
        },
        layoutType = layoutType,
        containerColor = Color.Transparent,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContentColor = MtmNavigationDefaults.navigationContentColor(),
            navigationRailContainerColor = Color.Transparent,
        ),
        modifier = modifier,
    ) {
        content()
    }
}

/**
 * A wrapper around [NavigationSuiteScope] to declare navigation items.
 */
class MtmNavigationSuiteScope internal constructor(
    private val navigationSuiteScope: NavigationSuiteScope,
    private val navigationSuiteItemColors: NavigationSuiteItemColors,
) {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        selectedIcon: @Composable () -> Unit = icon,
        label: @Composable (() -> Unit)? = null,
    ) = navigationSuiteScope.item(
        selected = selected,
        onClick = onClick,
        icon = {
            if (selected) {
                selectedIcon()
            } else {
                icon()
            }
        },
        label = label,
        colors = navigationSuiteItemColors,
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
fun MtmNavigationBarPreviews() {
    val items = listOf("Home", "Profile")
    val icons = listOf(
        MtmIcons.Home,
        MtmIcons.Profile,
    )
    val selectedIcons = listOf(
        MtmIcons.HomeSelected,
        MtmIcons.ProfileSelected,
    )

    MtmTheme {
        MtmNavigationBar {
            items.forEachIndexed { index, item ->
                NiaNavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(icons[index]),
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            painter = painterResource(selectedIcons[index]),
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}


object MtmNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant

    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer

    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}
