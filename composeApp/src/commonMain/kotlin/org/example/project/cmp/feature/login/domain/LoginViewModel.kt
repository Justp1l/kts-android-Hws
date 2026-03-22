package org.example.project.cmp.feature.login.domain

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.cmp.feature.login.domain.component.BaseViewModel
import org.example.project.cmp.feature.onBoard.data.AppStorage
import org.example.project.cmp.feature.login.domain.component.LoginUIState
import org.example.project.cmp.feature.login.domain.component.LoginUiEvent

class LoginViewModel(private val storage: AppStorage) :
    BaseViewModel<LoginUiEvent.LoginSuccessEvent, LoginUIState>(initialState = LoginUIState.Companion.initial) {

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
            } else {
                updateState {
                    copy(
                        error = true,
                        isTextFieldsActive = true
                    )
                }
            }
            if (isSuccess) completeOnboarding()
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            storage.setFirstLaunchCompleted()
        }
    }

}