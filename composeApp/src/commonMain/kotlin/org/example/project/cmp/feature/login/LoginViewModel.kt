package org.example.project.cmp.feature.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.cmp.common.BaseViewModel
import org.example.project.cmp.feature.login.presentation.LoginUIState
import org.example.project.cmp.feature.login.presentation.LoginUiEvent

class LoginViewModel :
    BaseViewModel<LoginUiEvent.LoginSuccessEvent, LoginUIState>(initialState = LoginUIState.initial) {

    fun onUsernameChange(value: String) {
        updateState { copy(username = value) }
    }

    fun onPasswordChange(value: String) {
        updateState { copy(password = value) }
    }

    fun clickOnLogin() {
        if (!state.value.isTextFieldsActive) return

        updateState {
            copy(
                error = false,
                isTextFieldsActive = false
            )
        }

        viewModelScope.launch {
            delay(1000)

            val isSuccess = true
            if (isSuccess) {
                acceptLabel(LoginUiEvent.LoginSuccessEvent)
            } else{
                updateState{
                    copy(
                        error = true,
                        isTextFieldsActive = true
                    )
                }
            }
        }
    }
}