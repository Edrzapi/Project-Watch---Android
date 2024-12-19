package uk.co.devfoundry.projectwatch.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.co.devfoundry.projectwatch.R
import uk.co.devfoundry.projectwatch.page.ui.CustomTextField
import uk.co.devfoundry.projectwatch.page.ui.OutlinedPrimaryButton
import uk.co.devfoundry.projectwatch.page.ui.PrimaryButton
import uk.co.devfoundry.projectwatch.ui.theme.ProjectWatchTheme
import uk.co.devfoundry.projectwatch.viewmodel.LoginEvent
import uk.co.devfoundry.projectwatch.viewmodel.LoginViewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectWatchTheme {
                LoginView()
            }
        }
    }
}
@Composable
fun LoginView(navController: NavController? = null, viewModel: LoginViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    // Observe the navigation event
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                LoginEvent.NavigateToHome -> {
                    navController?.navigate("home")  // Replace with actual destination
                }
            }
        }
    }

    // Accessing theme values
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val backgroundColor = MaterialTheme.colorScheme.background
    val buttonShape = MaterialTheme.shapes.medium
    val textStyle = MaterialTheme.typography.bodyMedium
    val padding = 16.dp
    val largePadding = 40.dp
    val fieldHeight = 60.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Centered Logo
            Image(
                painter = painterResource(id = R.drawable.full_logo_transparent),
                contentDescription = "App Logo",
                modifier = Modifier
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Input Fields
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = largePadding)
            ) {
                CustomTextField(
                    value = state.username,
                    onValueChange = { viewModel.onUsernameChange(it) },
                    hint = "Username",
                    modifier = Modifier.height(fieldHeight)
                )
                CustomTextField(
                    value = state.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    hint = "Password",
                    isPassword = true,
                    modifier = Modifier.height(fieldHeight)
                )
            }

            // Error Message
            state.errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = textStyle,
                    modifier = Modifier.padding(horizontal = largePadding)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Column to stack the buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = largePadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Submit Button using the reusable utility function

                PrimaryButton(
                    onClick = { viewModel.onLoginClicked() },
                    text = "Submit",
                    modifier = Modifier.height(fieldHeight)
                )

                // Forgot Password Button (Outlined) using the reusable utility function
                OutlinedPrimaryButton(
                    onClick = {
                        navController?.navigate("forgotPassword")
                    },
                    text = "Forgot your password?",
                    modifier = Modifier.height(fieldHeight)
                )
            }

            // Loading Spinner
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = primaryColor,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevLoginView() {
    ProjectWatchTheme {
        LoginView()
    }
}
