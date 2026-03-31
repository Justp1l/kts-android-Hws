package org.example.spaceShape.cmp.feature.login.domain.component

sealed class LoginUiEvent{
    data object LoginSuccessEvent : LoginUiEvent()
}