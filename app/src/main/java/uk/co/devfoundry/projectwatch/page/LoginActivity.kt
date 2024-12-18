package uk.co.devfoundry.projectwatch.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uk.co.devfoundry.projectwatch.R
import uk.co.devfoundry.projectwatch.ui.theme.ProjectWatchTheme

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
fun LoginView(navController: NavController? = null) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    // Accessing theme values
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val backgroundColor = MaterialTheme.colorScheme.background
    val buttonShape = MaterialTheme.shapes.medium
    val textStyle = MaterialTheme.typography.bodyMedium
    val padding = 16.dp
    val largePadding = 40.dp

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
                    value = username,
                    onValueChange = { username = it },
                    label = "Username"
                )

                CustomTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Password",
                    isPassword = true
                )
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

            Spacer(modifier = Modifier.height(20.dp))

            // Column to stack the buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = largePadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Submit Button
                Button(
                    onClick = {
                        if (username.isBlank() || password.isBlank()) {
                            errorMessage = "Please fill in all fields"
                        } else {
                            isLoading = true
                            // Simulate login action
                            isLoading = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryColor,
                        contentColor = onPrimaryColor
                    ),
                    shape = buttonShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Submit")
                }

                // Forgot Password Button (Outlined)
                OutlinedButton(
                    onClick = {
                        navController?.navigate("forgotPassword")
                    },
                    border = BorderStroke(1.dp, primaryColor),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = primaryColor
                    ),
                    shape = buttonShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Forgot your password?")
                }
            }

            // Loading Spinner
            if (isLoading) {
                CircularProgressIndicator(
                    color = primaryColor,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF2F2F7), // iOS-like light gray
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.Transparent, // Remove border background
            focusedBorderColor = Color.Transparent, // No border on focus
            unfocusedBorderColor = Color.Transparent, // No border when unfocused
            cursorColor = Color(0xFF007AFF) // iOS Blue Cursor
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PrevLoginView() {
    ProjectWatchTheme {
        LoginView()
    }
}
