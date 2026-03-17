package org.example.project.cmp.feature.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.cmp.feature.login.presentation.LoginUIState
import org.example.project.cmp.feature.login.presentation.LoginUiEvent
import org.example.project.theme.ShuttleTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel { LoginViewModel() },
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
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = ShuttleTheme.colors.background,
        contentColor = ShuttleTheme.colors.onBackground,
        contentWindowInsets = WindowInsets()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            HeaderSection()
            Spacer(
                Modifier
                    .size(10.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    LoginField(
                        login = state.username,
                        enabled = !state.isTextFieldsActive,
                        onUsernameChange = onUsernameChange
                    )
                    Spacer(
                        Modifier
                            .size(10.dp)
                    )
                    PasswordField(
                        password = state.password,
                        enabled = !state.isTextFieldsActive,
                        onValueChange = onPasswordChange
                    )
                    Spacer(
                        Modifier
                            .size(20.dp)
                    )
                    Button(
                        onClick = onLoginClick,
                        shape = AbsoluteCutCornerShape(1.dp),

                        modifier = Modifier
                            .width(200.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ShuttleTheme.colors.container,
                            contentColor = ShuttleTheme.colors.onContainer
                        ),
                        enabled = !state.isTextFieldsActive
                    ) {
                        if (state.isTextFieldsActive) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(24.dp),
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
            Spacer(
                Modifier.size(40.dp)
            )
        }
    }
}


@Composable
//@Preview
fun PasswordPreview() {
    MaterialTheme {
        PasswordField(
            password = "",
            enabled = true,
            onValueChange = {}
        )
    }
}

@Composable
@Preview
fun LoginPreview() {
    ShuttleTheme {
        LoginScreen(
            viewModel = LoginViewModel(),
            onNavigateToMainScreen = { }
        )
    }
}
