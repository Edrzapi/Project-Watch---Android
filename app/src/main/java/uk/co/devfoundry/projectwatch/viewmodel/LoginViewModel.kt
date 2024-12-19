package uk.co.devfoundry.projectwatch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

data class LoginState(
    val username: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    // SharedFlow to emit one-time events (navigation or success)
    private val _navigationEvent = MutableSharedFlow<LoginEvent>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onUsernameChange(newUsername: String) {
        _state.value = _state.value.copy(username = newUsername)
    }

    fun onPasswordChange(newPassword: String) {
        _state.value = _state.value.copy(password = newPassword)
    }

    fun onLoginClicked() {
        if (_state.value.username.isBlank() || _state.value.password.isBlank()) {
            _state.value = _state.value.copy(errorMessage = "Please fill in all fields")
            return
        }

        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch {
            kotlinx.coroutines.delay(2000)  // Simulating network delay
            _state.value = _state.value.copy(isLoading = false)

            // Simulate successful login and trigger navigation
            _navigationEvent.emit(LoginEvent.NavigateToHome)  // Emit navigation event
        }
    }
}

// Sealed class to represent possible navigation events
sealed class LoginEvent {
    object NavigateToHome : LoginEvent()
}
