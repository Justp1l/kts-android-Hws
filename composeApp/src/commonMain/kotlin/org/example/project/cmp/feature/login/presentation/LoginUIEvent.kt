package org.example.project.cmp.feature.login.presentation

sealed class LoginUiEvent{
    data object LoginSuccessEvent : LoginUiEvent()
}