package br.eti.rafaelcouto.rbscompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.eti.rafaelcouto.rbscompose.R
import br.eti.rafaelcouto.rbscompose.navigation.RBSComposeNavHost
import br.eti.rafaelcouto.rbscompose.viewmodel.MainViewModel
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.androidx.compose.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            KoinAndroidContext {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RBSComposeApp(navController = navController)
                }
            }
        }
    }
}

@Composable
fun RBSComposeApp(navController: NavHostController = rememberNavController()) {
    val viewModel: MainViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()

    RBSComposeApp(
        topAppBarTitle = stringResource(id = state.title),
        showsBackButton = state.hasBackButton,
        onBackButtonPressed = navController::popBackStack,
        content = {
            RBSComposeNavHost(
                navController = navController,
                onRouteChanged = viewModel::updateState
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RBSComposeApp(
    topAppBarTitle: String = "",
    showsBackButton: Boolean = false,
    onBackButtonPressed: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = topAppBarTitle) },
                navigationIcon = {
                    if (showsBackButton)
                        IconButton(
                            modifier = Modifier.semantics {
                                testTag = "backButton"
                            },
                            onClick = onBackButtonPressed
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "voltar"
                            )
                        }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.purple_200),
                    titleContentColor = colorResource(id = R.color.white),
                    navigationIconContentColor = colorResource(id = R.color.white),
                    actionIconContentColor = colorResource(id = R.color.white)
                )
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                content()
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun RBSComposeAppPreview() {
    RBSComposeApp(
        topAppBarTitle = "Home",
        showsBackButton = false,
        content = {
            Text(text = "Hello, world!")
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun RBSComposeAppBackButtonPreview() {
    RBSComposeApp(
        topAppBarTitle = "Usuário",
        showsBackButton = true,
        content = {
            Text(text = "Hello, world!")
        }
    )
}
