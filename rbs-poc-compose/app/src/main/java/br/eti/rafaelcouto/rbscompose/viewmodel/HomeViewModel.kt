package br.eti.rafaelcouto.rbscompose.viewmodel

import androidx.lifecycle.ViewModel
import br.eti.rafaelcouto.rbscompose.model.User
import br.eti.rafaelcouto.rbscompose.ui.state.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val homeState = MutableStateFlow(HomeScreenUiState())
    val homeUiState
        get() = homeState.asStateFlow()

    val user
        get() = User(
            username = homeState.value.username,
            password = homeState.value.password
        )

    init {
        homeState.update { state ->
            state.copy(
                onUsernameChange = { username ->
                    homeState.update {
                        it.copy(username = username)
                    }
                    onValuesChanged()
                },
                onPasswordChange = { password ->
                    homeState.update {
                        it.copy(password = password)
                    }
                    onValuesChanged()
                }
            )
        }
    }

    private fun onValuesChanged() {
        updateGreetings()
        updateButtonState()
    }

    private fun updateButtonState() {
        homeState.update {
            it.copy(
                isButtonActive = it.password.isNotEmpty() && it.username.isNotEmpty()
            )
        }
    }

    private fun updateGreetings() {
        homeState.update {
            it.copy(
                greetings = if (it.username.isEmpty())
                    "Preencha seus dados!"
                else
                    "Olá, ${it.username}!"
            )
        }
    }
}
