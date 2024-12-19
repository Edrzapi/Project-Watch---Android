import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import uk.co.devfoundry.projectwatch.page.ui.CustomTextField
import uk.co.devfoundry.projectwatch.page.ui.PrimaryButton
import uk.co.devfoundry.projectwatch.ui.theme.ProjectWatchTheme

class MapActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectWatchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MapView()
                }
            }
        }
    }
}

@Composable
fun MapView(navController: NavController? = null, viewModel: MapViewModel = viewModel()) {
    val markers by viewModel.markers.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val fieldHeight = 60.dp
    // Corrected CameraPositionState initialization
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(51.5074, -0.1278), 10f
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // Map View Fullscreen
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            markers.forEach { location ->
                Marker(
                    state = rememberMarkerState(position = location),
                    title = "Data Point",
                    snippet = "Lat: ${location.latitude}, Lng: ${location.longitude}"
                )
            }
        }
        // Search UI Overlaid
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
                .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                .padding(16.dp)
        ) {
            CustomTextField(
                value = viewModel.getPostcode(),
                onValueChange = { viewModel.updatePostcode(it) },
                hint =  "Enter Postcode" ,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            PrimaryButton(
                onClick = { viewModel.searchPostcode(this as Context) },
                text = "Submit",
                modifier = Modifier.height(fieldHeight)
            )
        }

        // Loading Indicator Overlaid
        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
                    .align(Alignment.Center)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview
@Composable
fun PrevMapView(modifier: Modifier = Modifier) {
    MapView()
}
