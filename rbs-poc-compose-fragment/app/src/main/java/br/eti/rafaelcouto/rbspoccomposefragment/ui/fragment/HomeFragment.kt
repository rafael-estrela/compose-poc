package br.eti.rafaelcouto.rbspoccomposefragment.ui.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.eti.rafaelcouto.rbspoccomposefragment.ui.state.HomeScreenUiState
import br.eti.rafaelcouto.rbspoccomposefragment.ui.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

class HomeFragment : BaseFragment() {

    override val hasBackButton: Boolean = false
    override val title: String = "Home"

    @Composable
    override fun ScreenContent() {
        val viewModel: HomeViewModel = koinViewModel()
        val state by viewModel.homeUiState.collectAsStateWithLifecycle(viewLifecycleOwner)

        HomeScreen(
            state,
            onSave = {
                val user = viewModel.user
                val direction = HomeFragmentDirections.actionHomeToUser(user)
                navController.navigate(direction)
            }
        )
    }
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState(),
    onSave: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            fontSize = 14.sp,
            text = state.greetings
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.username,
            onValueChange = state.onUsernameChange,
            singleLine = true,
            label = {
                Text(text = "Username")
            },
            placeholder = {
                Text(text = "Username")
            }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.password,
            onValueChange = state.onPasswordChange,
            singleLine = true,
            label = {
                Text(text = "Senha")
            },
            placeholder = {
                Text(text = "Senha")
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSave,
            enabled = state.isButtonActive
        ) {
            Text(text = "Salvar dados")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenFilledPreview() {
    HomeScreen(
        state = HomeScreenUiState(
            greetings = "Olá, John!",
            username = "John",
            password = "1234",
            isButtonActive = true
        )
    )
}
