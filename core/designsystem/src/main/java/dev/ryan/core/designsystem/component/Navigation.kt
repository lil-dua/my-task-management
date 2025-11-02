package dev.ryan.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
