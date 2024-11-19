package br.eti.rafaelcouto.rbscompose.di

import br.eti.rafaelcouto.rbscompose.model.User
import br.eti.rafaelcouto.rbscompose.viewmodel.HomeViewModel
import br.eti.rafaelcouto.rbscompose.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HomeViewModel)

    viewModel { (user: User) ->
        UserViewModel(user = user)
    }
}
