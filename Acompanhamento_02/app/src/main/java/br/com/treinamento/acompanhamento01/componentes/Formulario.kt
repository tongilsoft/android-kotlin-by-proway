package br.com.treinamento.acompanhamento01.componentes

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.PermContactCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.treinamento.acompanhamento01.modelos.Colaborador

@Composable
fun Formulario(
    acaoCadastro: (Colaborador) -> Unit,
    editEnabled: Boolean,
    toEdit: Colaborador?,
    acaoEdicao: (Colaborador) -> Unit,
    acaoCancelar: () -> Unit
){

    // Variaveis
    val context = LocalContext.current
    var nome by remember(toEdit) { mutableStateOf(toEdit?.nome?:"") }
    var email by remember(toEdit) { mutableStateOf(toEdit?.email?:"") }
    var nivel by remember(toEdit) { mutableStateOf(toEdit?.nivel?:"") }
    var focusMgr = LocalFocusManager.current
    val scrollState = rememberScrollState()

    // Estrutura (layout)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE9FFAD).copy(alpha = 0.9f))
            .padding(all = 15.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        if (editEnabled){
            Text(
                text = "Editando colaborador",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Blue),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                text = "Cadastrando colaborador",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
        // Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome:") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.PermContactCalendar,
                    contentDescription = null
                )
            }
        )

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email:") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null
                )
            }
        )


        // Nivel
        val niveis = remember {
            mutableListOf(
                "gerência",
                "administrativo",
                "financeiro",
                "suporte"
            )
        }
        var expanded by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = nivel,
            onValueChange = { nivel = it },
            label = { Text("Nivel:") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Groups,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Selecionar nivel",
                        modifier = Modifier.background(color=Color.Gray)
                    )
                }
            }
        )
        // Caixa onde viasualizaremos os niveis para o usuário selecionar um
        Box() {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(color = Color.Black)
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = "Selecionar nivel:",
                    color = Color.White
                )
                for (nvl in niveis){
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "${nvl}",
                                color = Color.White
                            )
                        },
                        onClick = {
                            nivel = nvl
                            expanded = false
                        }
                    )
                }
            }
            LaunchedEffect(expanded) {
                if (expanded) {
                    scrollState.scrollTo(scrollState.maxValue)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (editEnabled) {
                Button(
                    onClick = {
                        nome = ""
                        email = ""
                        nivel = ""
                        focusMgr.clearFocus()
                        acaoCancelar()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Cancelar")
                }
            }

            Button(
                onClick = {
                    if (nome.isEmpty() || email.isEmpty() || nivel.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Preencha todos os dados do colaborador para poder cadastrar",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        // Cadastrar
                        if (editEnabled && toEdit != null) {
                            acaoEdicao(Colaborador(toEdit.idColaborador, nome, email, nivel))
                        } else {
                            acaoCadastro(Colaborador(0, nome, email, nivel))
                        }
                        // Limpar variaveis
                        nome = ""
                        email = ""
                        nivel = ""

                        // Mudar o focus
                        focusMgr.clearFocus()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = if (editEnabled) Color.Blue else Color.Black)
            ) {
                Text(if (editEnabled) "Salvar" else "Cadastrar")
            }
        }
    }
}