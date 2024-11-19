package br.eti.rafaelcouto.rbscompose.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String = "",
    val password: String = ""
)
