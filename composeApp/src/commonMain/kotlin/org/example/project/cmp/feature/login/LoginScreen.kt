package org.example.project.cmp.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.map
import kts_hw2.composeapp.generated.resources.Res
import kts_hw2.composeapp.generated.resources.astronaut
import kts_hw2.composeapp.generated.resources.unispace_bd
import org.example.project.cmp.feature.login.presentation.LoginUIState
import org.example.project.cmp.feature.login.presentation.LoginUiEvent
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource


@Composable
@Stable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel { LoginViewModel() },
    onNavigateToGreeting: () -> Unit,
    onNavigateToMainScreen: () -> Unit

) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.label.collect { label ->
            when (label) {
                LoginUiEvent.LoginSuccessEvent -> onNavigateToMainScreen()
            }

        }
    }
    val email = "ytug776@gmail.com"

    val annotation = buildAnnotatedString {
        append("Если Вы хотите зарегистрироваться, пишите на\n")
        withLink(
            LinkAnnotation.Url(
                url = "mailto:$email",
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    )
                )
            )
        ) {
            append(email)
        }
    }

    LoginContent(
        state = state,
        annotation = annotation,
        onUsernameChange = viewModel::onUsernameChange,
        onPasswordChange = viewModel::onPasswordChange,
        onLoginClick = viewModel::clickOnLogin,
    )
}

@Composable

fun LoginContent(
    state: LoginUIState,
    annotation: AnnotatedString,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

           HeaderSection()
            Spacer(Modifier.padding(10.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    LoginField(
                        state = state,
                        onUsernameChange = onUsernameChange
                    )
                    Spacer(Modifier.padding(10.dp))
                    PasswordField(
                        state = state,
                        onValueChange = onPasswordChange
                    )
                    Spacer(Modifier.padding(20.dp))
                    Button(
                        onClick = onLoginClick,
                        shape = AbsoluteCutCornerShape(1.dp),

                        modifier = Modifier.width(200.dp).height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        enabled = !state.isLoginButtonActive
                    ) {
                        if (state.isLoginButtonActive) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = MaterialTheme.colorScheme.background,
                                strokeWidth = 2.dp,
                            )
                        } else {
                            Text("LOGIN")
                        }
                    }
                }
            }
            Box() {
                Text(text = annotation, textAlign = TextAlign.Center)
            }
            Spacer(Modifier.padding(40.dp))
        }
    }
}

@Composable
fun HeaderSection() {
    Column(
        modifier = Modifier.padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                resource = Res.drawable.astronaut
            ),
            contentDescription = "Astronaut and Mars",
            modifier = Modifier.size(width = 190.dp, height = 250.dp)
        )
        Text(
            modifier = Modifier,
            fontFamily = FontFamily(Font(Res.font.unispace_bd)),
            color = Color.Red,
            text = "Sign in",
            fontSize = 35.sp,
        )
    }
}

@Composable
fun LoginField(state: LoginUIState,
               onUsernameChange: (String) -> Unit) {
    TextField(
        value = state.username,
        onValueChange = onUsernameChange,
        placeholder = { Text(text = "Login", textAlign = TextAlign.Center) },
        label = { Text("Email") },
        singleLine = true,
        isError = state.error,
        enabled = !state.isLoginButtonActive,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
        modifier = Modifier
            .border(1.dp, Color.Black, RectangleShape)
            .padding(),

        )
}
@Composable
fun PasswordField(
    state: LoginUIState,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = state.password,
        onValueChange = onValueChange,
        //label = { Text(text = "Password") },
        placeholder = { Text(text = "Password", textAlign = TextAlign.Center) },
        modifier = Modifier
            .border(1.dp, Color.Black, RectangleShape)
            .padding(),
        visualTransformation = if (passwordVisible) VisualTransformation.None
        else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                Icon(
                    //fix to eye later
                    imageVector = Icons.Default.Lock,
                    contentDescription = if (passwordVisible)
                        "Hide password" else "Show password"
                )
            }
        },
    )
}

@Composable
//@Preview
fun PasswordPreview() {
    MaterialTheme {
        PasswordField(
            state = LoginUIState.initial,
            onValueChange = {}
        )
    }
}

@Composable
@Preview
fun LoginPreview() {
    MaterialTheme {
        LoginScreen(
            viewModel = LoginViewModel(),
            onNavigateToGreeting = { },
            onNavigateToMainScreen = { }
        )
    }
}
