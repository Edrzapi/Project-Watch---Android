package uk.co.devfoundry.projectwatch

import MapViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.co.devfoundry.projectwatch.page.ui.CustomTextField
import uk.co.devfoundry.projectwatch.page.ui.PrimaryButton
import uk.co.devfoundry.projectwatch.ui.theme.ProjectWatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ProjectWatchTheme {
                AppNav(navController)
            }
        }
    }
}

@Composable
fun HomeView(navController: NavController? = null) {
    Box(modifier = Modifier.fillMaxSize()) {
        NavigationTopBar(navController)
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SearchPostcode(navController, viewModel = MapViewModel())
        }
    }
}
@Composable
fun SearchPostcode(
    navController: NavController? = null,
    viewModel: MapViewModel,
    modifier: Modifier = Modifier
) {
    val postcode by viewModel.postcode.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            value = postcode,
            onValueChange = { viewModel.updatePostcode(it) },
            hint = "Enter Postcode",
            modifier = Modifier.fillMaxWidth(),
            isError = postcode.isBlank()
        )

        Spacer(modifier = Modifier.height(15.dp))

        PrimaryButton(
            onClick = {
                if (postcode.isNotBlank()) {
                    navController?.navigate("map")
                }
            },
            text = "Submit"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSearchPostcode() {
    ProjectWatchTheme {
        SearchPostcode(viewModel = MapViewModel())
    }
}
