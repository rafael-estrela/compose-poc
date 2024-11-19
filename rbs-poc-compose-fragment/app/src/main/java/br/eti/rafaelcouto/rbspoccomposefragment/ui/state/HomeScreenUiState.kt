package br.eti.rafaelcouto.rbspoccomposefragment.ui.state

data class HomeScreenUiState(
    val greetings: String = "Preencha seus dados!",
    val isButtonActive: Boolean = false,
    val username: String = "",
    val onUsernameChange: (String) -> Unit = {},
    val password: String = "",
    val onPasswordChange: (String) -> Unit = {}
)
