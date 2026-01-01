package dev.ryan.feature.task.tasklist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.ryan.core.designsystem.component.MtmBackground
import dev.ryan.core.designsystem.theme.MtmTheme
import dev.ryan.core.ui.DevicePreviews
import java.util.Date

@Composable
internal fun TaskListRoute(
    modifier: Modifier = Modifier,
    viewModel: TaskListViewModel = hiltViewModel(),
    onNavigateToHome: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    val profileState by viewModel.uiState.collectAsState()
    TaskListScreen(
        profileState = profileState,
        modifier = modifier,
    )

}
@Composable
fun TaskListScreen(
    profileState: TaskListUiState,
    modifier: Modifier
) {

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("List Task Screen")
        }
        FloatingActionButton(
            modifier = modifier.size(46.dp),
            onClick = {}

        ) {
            Icon(
                painter = painterResource(dev.ryan.core.designsystem.R.drawable.ic_add_task),
                contentDescription = null
            )
        }
    }


}

@Composable
private fun TaskListHeaderSection(
    date: Date,
    userAvatar: Painter,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = "Monday",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                )
            )
            Text(
                text = "25 September",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
            )
        }

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // search button
            OutlinedIconButton(
                onClick = onNotificationClick,
                modifier = modifier.size(56.dp)
            ) {
                Icon(
                    painter = painterResource(dev.ryan.core.designsystem.R.drawable.ic_add_task),
                    contentDescription = null
                )
            }

            Spacer(modifier = modifier.width(20.dp))

            // profile image
            Image(
                modifier = modifier
                    .background(
                        shape = CircleShape, color = Color.Black
                    )
                    .size(56.dp)
                    .clickable(onClick = onProfileClick),
                painter = userAvatar,
                contentDescription = "User avatar"
            )
        }
    }

}

@DevicePreviews
@Composable
fun TaskListScreenPreview() {
    MtmTheme {
        MtmBackground {
            TaskListScreen(
                profileState = TaskListUiState(),
                modifier = Modifier
            )
        }
    }
}

