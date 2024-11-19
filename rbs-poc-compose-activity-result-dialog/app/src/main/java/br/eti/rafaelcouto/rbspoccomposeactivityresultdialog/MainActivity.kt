package br.eti.rafaelcouto.rbspoccomposeactivityresultdialog

import android.Manifest
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.eti.rafaelcouto.rbspoccomposeactivityresultdialog.ui.theme.RbspoccomposeactivityresultdialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RbspoccomposeactivityresultdialogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    val (isDialogOpen, setIsDialogOpen) = remember { mutableStateOf(false) }
    val (result, setResult) = remember { mutableStateOf<Bitmap?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = setResult
    )

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) cameraLauncher.launch()
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .padding(all = 16.dp)
            .align(Alignment.Center)) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }
            ) {
                Text(text = "Capturar imagem")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { setIsDialogOpen(true) }
            ) {
                Text(text = "Abrir dialog")
            }
            
            result?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                        .clickable { setResult(null) }
                )
            }
        }

        if (isDialogOpen) {
            SelectorDialog(
                items = arrayOf("Argentina", "Brasil", "Colômbia"),
                onSelect = {
                    Toast.makeText(context, "Selecionou $it", Toast.LENGTH_SHORT).show()
                },
                onCancel = {
                    Toast.makeText(context, "Cancelou", Toast.LENGTH_SHORT).show()
                },
                onDismiss = { setIsDialogOpen(false) }
            )
        }
    }
}

@Composable
fun SelectorDialog(
    items: Array<String>,
    onSelect: (String) -> Unit = {},
    onCancel: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val (selectedItem, setSelectedItem) = remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp),
            content = {
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = "Selecione uma opção"
                )
                items.forEach {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = if (selectedItem == it) Color.LightGray else Color.Transparent)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .clickable { setSelectedItem(it) },
                        text = it
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                    content = {
                        TextButton(
                            onClick = {
                                onCancel()
                                onDismiss()
                            },
                            content = {
                                Text(text = "Cancelar")
                            }
                        )
                        TextButton(
                            onClick = {
                                onSelect(selectedItem)
                                onDismiss()
                            },
                            content = {
                                Text(text = "Selecionar")
                            }
                        )
                    }
                )
            }
        )
    }
}

@Preview//(showSystemUi = true)
@Composable
fun SelectorDialogPreview() {
    SelectorDialog(arrayOf("First", "Second", "Third"))
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    RbspoccomposeactivityresultdialogTheme {
        MainScreen()
    }
}
