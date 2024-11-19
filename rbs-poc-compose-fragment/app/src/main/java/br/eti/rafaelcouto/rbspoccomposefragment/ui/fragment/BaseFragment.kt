package br.eti.rafaelcouto.rbspoccomposefragment.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.eti.rafaelcouto.rbspoccomposefragment.R
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
abstract class BaseFragment : Fragment() {

    protected lateinit var navController: NavController

    protected abstract val hasBackButton: Boolean
    protected abstract val title: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )

            setContent {
                KoinAndroidContext {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RBSComposeFragmentApp(
                            hasBackButton,
                            title,
                            onBackButtonPressed = navController::popBackStack,
                            content = {
                                ScreenContent()
                            }
                        )
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }

    @Composable
    protected abstract fun ScreenContent()
}

@Composable
fun RBSComposeFragmentApp(
    hasBackButton: Boolean = false,
    title: String = "",
    onBackButtonPressed: () -> Unit = {},
    content: @Composable () -> Unit
) {
    RBSComposeFragmentApp(
        topAppBarTitle = title,
        showsBackButton = hasBackButton,
        onBackButtonPressed = onBackButtonPressed,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RBSComposeFragmentApp(
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
fun RBSComposeFragmentAppPreview() {
    RBSComposeFragmentApp(
        topAppBarTitle = "Home",
        showsBackButton = false,
        content = {
            Text(text = "Hello, world!")
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun RBSComposeFragmentAppBackButtonPreview() {
    RBSComposeFragmentApp(
        topAppBarTitle = "Usuário",
        showsBackButton = true,
        content = {
            Text(text = "Hello, world!")
        }
    )
}
