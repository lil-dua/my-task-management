package dev.ryan.feature.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
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
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToAddTask: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
) {
    val homeState by viewModel.uiState.collectAsState()
    HomeScreen(
        homeState = homeState,
        modifier = modifier,
        onNavigateToProfile = onNavigateToProfile
    )
}

@Composable
fun HomeScreen(
    homeState: HomeUiState,
    modifier: Modifier = Modifier,
    onNavigateToProfile: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 24.dp, end = 24.dp)
    ) {

        // Header section
        HomeHeaderSection(
            date = Date(),
            userAvatar = painterResource(dev.ryan.core.designsystem.R.drawable.ic_profile_selected),
            onNotificationClick = {},
            onProfileClick = onNavigateToProfile
        )

        Spacer(Modifier.height(26.dp))

        // Greeting user
        Text("Hello,")
        Text(
            text = homeState.userName,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W700
            )
        )

        Spacer(modifier.height(26.dp))

        homeState.taskTypes.forEach { (type, count) ->
            Text("$type: $count tasks")
        }

        // Progress chart
        MonthlyProgressChart(progress = 12f)

        Spacer(modifier.height(26.dp))
        // StatsGrid
        StatsGrid(modifier = modifier)
    }
}

@Composable
private fun HomeHeaderSection(
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

@Composable
private fun MonthlyProgressChart(
    progress: Float,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(
                color = Color.Cyan,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(20.dp)
    ) {
        val totalTaskPerMonth = 55
        val sweepTodo = 12f / totalTaskPerMonth * 360f
        val sweepInProgress = 7f / totalTaskPerMonth * 360f
        val sweepDone = 22f / totalTaskPerMonth * 360f
        val sweepPending = 14f / totalTaskPerMonth * 360f
        val strokeWidth = 56f

        Canvas(
            modifier = modifier.size(80.dp)
        ) {
            var startAngle = -90f

            fun drawSegment(color: Color, sweep: Float) {
                drawArc(
                    color = color,
                    startAngle = startAngle,
                    sweepAngle = sweep,
                    useCenter = false,
                    style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
                )
                startAngle += sweep
            }

            drawSegment(Color.Green, sweepDone)
            drawSegment(Color.Blue, sweepInProgress)
            drawSegment(Color.Red, sweepPending)
            drawSegment(Color.Gray, sweepTodo)
        }
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier) {
            TaskProgressBar("Done", 12, 25, Color.Green)
            Spacer(Modifier.height(16.dp))
            TaskProgressBar("In Progress", 17, 20, Color.Blue)
        }

    }


}

@Composable
fun TaskProgressBar(label: String, value: Int, total: Int, color: Color) {
    val percent = value * 100f / total

    Column {
        Row {
            Text(label, modifier = Modifier.weight(1f))
            Text("${percent.toInt()}%")
        }
        LinearProgressIndicator(
            progress = value / total.toFloat(),
            color = color,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .height(10.dp),
            trackColor = Color.LightGray.copy(alpha = 0.3f)
        )
    }
}

@Composable
private fun StatsGrid(
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        modifier = modifier
    ) {
        item {
            StatsCard(
                title = "In Progress",
                numberOfTicket = 10,
                background = Color.Blue
            )
        }
        item {
            StatsCard(
                title = "To Do",
                numberOfTicket = 10,
                background = Color.Gray
            )
        }
        item {
            StatsCard(
                title = "Done",
                numberOfTicket = 10,
                background = Color.Green
            )
        }
        item {
            StatsCard(
                title = "Depending",
                numberOfTicket = 10,
                background = Color.Red
            )
        }
    }
}

@Composable
private fun StatsCard(
    title: String,
    numberOfTicket: Int,
    background: Color,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                shape = RoundedCornerShape(8.dp),
                brush = Brush.verticalGradient(
                    0.0f to Color.White,
                    1.0f to background,
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(all = 20.dp)
        ) {
            Text(text = title)
            Spacer(Modifier.size(10.dp))
            Text(text = numberOfTicket.toString())
        }
    }
}


@DevicePreviews
@Composable
fun HomeScreenPreview() {
    MtmTheme {
        MtmBackground {
            HomeScreen(
                homeState = HomeUiState(),
                onNavigateToProfile = {}
            )
        }
    }
}
