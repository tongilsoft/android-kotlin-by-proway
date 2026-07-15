package br.com.treinamento.acompanhamento01.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AcoesListagem(acaoEdit: () -> Unit, acaoDelete: () -> Unit){
    Column(){
        IconButton(
            onClick = acaoEdit,
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Blue),
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "Editar"
            )
        }
        IconButton(
            onClick = acaoDelete,
            colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Red),
        ) {
            Icon(
                imageVector = Icons.Outlined.DeleteForever,
                contentDescription = "Deletar"
            )
        }
    }
}