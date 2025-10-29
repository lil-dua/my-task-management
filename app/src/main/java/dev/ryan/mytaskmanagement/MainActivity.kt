package dev.ryan.mytaskmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.ryan.core.designsystem.theme.MtmTheme
import dev.ryan.core.ui.DevicePreviews
import dev.ryan.feature.home.HomeScreen
import dev.ryan.feature.home.navigation.HomeRoute
import dev.ryan.mytaskmanagement.ui.MtmApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MtmTheme {
                MtmApp()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@DevicePreviews
@Composable
fun GreetingPreview() {
    MtmTheme {
        Greeting("Android")
    }
}