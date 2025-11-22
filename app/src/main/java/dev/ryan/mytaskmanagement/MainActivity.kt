package dev.ryan.mytaskmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.ryan.core.designsystem.theme.MtmTheme
import dev.ryan.core.ui.DevicePreviews
import dev.ryan.mytaskmanagement.ui.MtmApp
import dev.ryan.mytaskmanagement.ui.MtmAppState
import dev.ryan.mytaskmanagement.ui.rememberMtmAppState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState: MtmAppState = rememberMtmAppState()
            MtmTheme {
                MtmApp(appState)
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