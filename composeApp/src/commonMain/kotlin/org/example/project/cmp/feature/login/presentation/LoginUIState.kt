package org.example.project.cmp.feature.login.presentation

data class LoginUIState(
    val username:String,
    val password: String,
    val isLoginButtonActive: Boolean,
    val error: Boolean
) {
    companion object {
        val initial = LoginUIState(
            username = "",
            password = "",
            isLoginButtonActive = false,
            error = false
        )
    }
}