package uk.co.devfoundry.projectwatch.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uk.co.devfoundry.projectwatch.page.ui.CustomTextField
import uk.co.devfoundry.projectwatch.ui.theme.ProjectWatchTheme

class ForgotActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectWatchTheme {
                ForgotView()
            }
        }
    }
}
@Composable
fun ForgotView(navController: NavController? = null) {
    var email by remember { mutableStateOf("") }
    var isEmailSent by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
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
            // Title
            Text(
                text = "Forgot Password",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 120.dp)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Email Input Field
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                hint = "Email",
                isPassword = false,
                modifier = Modifier.height(fieldHeight).fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Submit Button
            Button(
                onClick = {
                    if (email.isBlank()) {
                        errorMessage = "Please enter your email"
                    } else {
                        handlePasswordReset(email) {
                            isEmailSent = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor,
                    contentColor = onPrimaryColor
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(fieldHeight) // Apply consistent height here
            ) {
                Text("Send Reset Link")
            }

            // Error Message
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = textStyle,
                    modifier = Modifier.padding(horizontal = largePadding)
                )
            }

            // Confirmation Message
            if (isEmailSent) {
                Text(
                    text = "A password reset link has been sent to your email.",
                    color = Color.Green,
                    style = textStyle,
                    modifier = Modifier.padding(horizontal = largePadding)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}


// Simulate sending reset link via email
private fun handlePasswordReset(email: String, onSuccess: () -> Unit) {
    // Simulate a network operation for sending a reset email
    onSuccess()
}

@Preview(showBackground = true)
@Composable
fun PreviewForgotView() {
    ProjectWatchTheme {
        ForgotView()
    }
}
