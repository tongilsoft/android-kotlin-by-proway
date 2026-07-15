package br.com.treinamento.acompanhamento01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.treinamento.acompanhamento01.telas.TelaCadastro
import br.com.treinamento.acompanhamento01.ui.theme.Acompanhamento01JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Acompanhamento01JetpackComposeTheme {
                TelaCadastro()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Acompanhamento01JetpackComposeTheme {
        TelaCadastro()
    }
}

