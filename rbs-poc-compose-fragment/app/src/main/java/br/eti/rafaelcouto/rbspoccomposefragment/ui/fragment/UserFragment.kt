package br.eti.rafaelcouto.rbspoccomposefragment.ui.fragment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.navArgs
import br.eti.rafaelcouto.rbspoccomposefragment.ui.state.UserScreenUiState
import br.eti.rafaelcouto.rbspoccomposefragment.ui.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

class UserFragment : BaseFragment() {

    override val hasBackButton: Boolean = true
    override val title: String = "Usuário"

    private val args by navArgs<UserFragmentArgs>()

    @Composable
    override fun ScreenContent() {
        val viewModel: UserViewModel = koinViewModel(parameters = {
            parametersOf(args.user)
        })

        val state by viewModel.userUiState.collectAsStateWithLifecycle(viewLifecycleOwner)

        UserScreen(state)
    }
}

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
