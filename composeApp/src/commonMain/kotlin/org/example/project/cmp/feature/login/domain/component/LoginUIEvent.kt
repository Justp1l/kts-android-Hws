package org.example.project.cmp.feature.login.domain.component

sealed class LoginUiEvent{
    data object LoginSuccessEvent : LoginUiEvent()
}