package dev.ryan.feature.home

import android.graphics.Canvas
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.ryan.core.designsystem.theme.GradientColors
import dev.ryan.core.designsystem.theme.LocalGradientColors
import dev.ryan.core.designsystem.theme.MtmTheme
import dev.ryan.core.ui.DevicePreviews
import dev.ryan.feature.home.navigation.HomeRoute
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
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    homeState: HomeUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Header section

        // Greeting user
        Text("Hello, ${homeState.userName}", style = MaterialTheme.typography.headlineSmall)
        Text("Today's progress: ${homeState.completedPercent}%")

        Spacer(modifier.height(16.dp))
        homeState.taskTypes.forEach { (type, count) ->
            Text("$type: $count tasks")
        }

        // Progress chart
        MonthlyProgressChart(progress = 12f)

        // StatsGrid
        StatsGrid(modifier = modifier)
    }
}

@Composable
private fun HomeHeaderSection(
    date: Date,
    userAvatar: BitmapPainter,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {

}

@Composable
private fun MonthlyProgressChart(
    progress: Float,
    modifier: Modifier = Modifier.size(100.dp)
) {
    Row {
        val totalTaskPerMonth = 55
        val sweepTodo = 12f / totalTaskPerMonth * 360f
        val sweepInProgress = 7f / totalTaskPerMonth * 360f
        val sweepDone = 22f / totalTaskPerMonth * 360f
        val sweepPending = 14f / totalTaskPerMonth * 360f
        val strokeWidth = 36f

        Canvas(modifier = modifier) {
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
        Spacer(modifier = Modifier.size(16.dp))

        Column(modifier = Modifier.weight(1f)) {
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
        modifier = modifier.padding(16.dp)
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
        HomeScreen(
            homeState = HomeUiState()
        )
    }
}
