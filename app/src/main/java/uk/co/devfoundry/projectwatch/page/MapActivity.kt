package uk.co.devfoundry.projectwatch.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import uk.co.devfoundry.projectwatch.ui.theme.ProjectWatchTheme

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectWatchTheme {
            }
        }
    }
}


@Composable
fun MapView(navController: NavController? = null) {
}
