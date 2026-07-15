package br.com.treinamento.acompanhamento01.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.treinamento.acompanhamento01.componentes.Formulario
import br.com.treinamento.acompanhamento01.componentes.Listagem
import br.com.treinamento.acompanhamento01.modelos.Colaborador

@Composable
fun TelaCadastro() {

    // Coleção de colaboradores
    val colaboradores = remember { mutableStateListOf<Colaborador>(
        Colaborador(
            1,
            "Tony Stark",
            "ceo@starkindustries.com",
            "gerência"
        ),
        Colaborador(
            2,
            "Pepper Potts",
            "manager@starkindustries.com",
            "gerência"
        )
    ) }

    var editMode by remember { mutableStateOf(false) }
    var editThis by remember { mutableStateOf<Colaborador?>(null) }

    var showDeleteDialog by remember { mutableStateOf(false) }
    var colabToDelete by remember { mutableStateOf<Colaborador?>(null) }

    // Função para gerar IDs únicos
    fun gerarProximoId(): Int {
        return if (colaboradores.isEmpty()) 1 else colaboradores.maxOf { it.idColaborador } + 1
    }

    // Estrutura
    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
    ) {

        // Formulario
        Formulario(
            acaoCadastro = { novoColab ->
                println("Cadastrando...")
                val colabComId = novoColab.copy(idColaborador = gerarProximoId())
                colaboradores.add(colabComId)
            },
            editEnabled = editMode,
            toEdit = editThis,
            acaoEdicao = { editColab ->
                println("Editando...")
                // Substituir o colaborador ao invés de remover e adicionar
                val index = colaboradores.indexOfFirst { it.idColaborador == editColab.idColaborador }
                if (index != -1) {
                    colaboradores[index] = editColab
                }
                editMode = false
                editThis = null
            },
            acaoCancelar = {
                editMode = false
                editThis = null
            }
        )

        // Listagem
        Listagem(
            colaboradores,
            acaoEdicao = { colabEdt ->
                println("Indo para Editar...")
                editThis = colabEdt
                editMode = true
            },
            acaoRemocao = { colabRem ->
                println("Removendo...")
                colabToDelete = colabRem
                showDeleteDialog = true
            }
        )

    }

    // Dialogo de confirmação de deleção
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = "Confirmação") },
            text = { Text(text = "Tem certeza que quer deletar?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        colabToDelete?.let { colaboradores.remove(it) }
                        showDeleteDialog = false
                        colabToDelete = null
                    }
                ) {
                    Text("Sim")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        colabToDelete = null
                    }
                ) {
                    Text("Não")
                }
            }
        )
    }
}