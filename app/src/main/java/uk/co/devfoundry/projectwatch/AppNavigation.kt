package uk.co.devfoundry.projectwatch

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.co.devfoundry.projectwatch.page.LoginView
import uk.co.devfoundry.projectwatch.page.MapView


@Composable
fun AppNav(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.LOGIN) {
        composable(Routes.HOME) { HomeView(navController) }
        composable(Routes.LOGIN) { LoginView(navController) }
        composable(Routes.MAP) { MapView(navController) }
    }
}


object Routes {
    const val HOME = "Home"
    const val MAP = "Map"
    const val LOGIN = "Login"

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTopBar(navController: NavController? = null) {
    val expanded = remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text("Project Watch") },
        navigationIcon = {
            IconButton(onClick = { expanded.value = !expanded.value }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }) {
                DropdownMenuItem(
                    text = { Text("Home") },
                    leadingIcon = {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    },
                    onClick = {
                        navController?.navigate(Routes.HOME)
                        expanded.value = false
                    },
                )
            }
        }
    )
}

