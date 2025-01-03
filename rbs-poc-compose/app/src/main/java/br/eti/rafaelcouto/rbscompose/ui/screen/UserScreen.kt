package br.eti.rafaelcouto.rbscompose.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.eti.rafaelcouto.rbscompose.ui.state.UserScreenUiState

@Composable
fun UserScreen(
    state: UserScreenUiState = UserScreenUiState()
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Username: ${state.username}"
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Senha: ${state.password}"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserScreenPreview() {
    UserScreen(
        state = UserScreenUiState(
            username = "John",
            password = "123456"
        )
    )
}
