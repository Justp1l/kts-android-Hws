package org.example.project.cmp.feature.login.domain.component

data class LoginUIState(
    val username:String,
    val password: String,
    val isTextFieldsActive: Boolean,
    val error: Boolean
) {
    companion object {
        val initial = LoginUIState(
            username = "",
            password = "",
            isTextFieldsActive = true,
            error = false
        )
    }
}