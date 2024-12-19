package uk.co.devfoundry.projectwatch.page.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    isPassword: Boolean = false,
    isError: Boolean = false,  // Added isError parameter
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(hint) },
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
        modifier = modifier
            .background(
                color = Color(0xFFF2F2F7),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        isError = isError,  // Pass isError to OutlinedTextField
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.Transparent,
            focusedBorderColor = if (isError) Color(0xFFFF3B30) else Color.Transparent,  // Red border on error
            unfocusedBorderColor = if (isError) Color(0xFFFF3B30) else Color.Transparent,  // Red border on error
            cursorColor = if (isError) Color(0xFFFF3B30) else Color(0xFF007AFF),  // Red cursor on error
            // Other colors remain unchanged
        )
    )
}

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = 60.dp
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val onPrimaryColor = MaterialTheme.colorScheme.onPrimary
    val buttonShape = MaterialTheme.shapes.medium

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor,
            contentColor = onPrimaryColor
        ),
        shape = buttonShape,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Text(text)
    }
}

@Composable
fun OutlinedPrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = 60.dp
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val buttonShape = MaterialTheme.shapes.medium

    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, primaryColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = primaryColor
        ),
        shape = buttonShape,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Text(text)
    }
}



