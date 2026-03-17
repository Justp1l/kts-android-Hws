package org.example.project.cmp.feature.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun LoginField(
    login: String,
    enabled: Boolean,
    onUsernameChange: (String) -> Unit
) {
    TextField(
        value = login,
        onValueChange = onUsernameChange,
        placeholder = { Text(text = "Login", textAlign = TextAlign.Center) },
        label = { Text("Email") },
        singleLine = true,
        //isError = state.error,
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
        ),
        modifier = Modifier
            .border(1.dp, Color.Black, RectangleShape)
    )
}

@Composable
fun PasswordField(
    password: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        //label = { Text(text = "Password") } Не включаю т.к поле поучается с двойными границами,
        placeholder = { Text(text = "Password", textAlign = TextAlign.Center) },
        modifier = Modifier
            .border(1.dp, Color.Black, RectangleShape)
            .padding(),
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        enabled = enabled,
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible },
            ) {
                Icon(
                    //fix to eye later
                    imageVector = Icons.Default.Lock,
                    contentDescription = if (passwordVisible)
                        "Hide password" else "Show password",
                    tint = Color.Black
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
        )
    )
}