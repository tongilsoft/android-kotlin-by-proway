package br.com.treinamento.acompanhamento01.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.treinamento.acompanhamento01.modelos.Colaborador


@Composable
fun Listagem(
    colaboradores: List<Colaborador>,
    acaoEdicao: (Colaborador) -> Unit,
    acaoRemocao: (Colaborador) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFD1F2FF).copy(alpha = 0.9f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(all = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Titulo
        Text(
            text = "Colaboradores cadastrados:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .background(Color.White.copy(alpha = 0.7f))
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Estrutura
        LazyColumn(
            modifier = Modifier.padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            // Laço de repetição
            itemsIndexed(colaboradores) { index, colab ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (index % 2 == 0) Color.Gray.copy(alpha = 0.9f) else Color.Black.copy(alpha = 0.1f)
                        )
                        .padding(vertical = 5.dp, horizontal = 5.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val icone = when (colab.nivel.lowercase()) {
                        "gerência" -> Icons.Default.Business
                        "administrativo" -> Icons.Default.AccountBalance
                        "financeiro" -> Icons.Default.AttachMoney
                        "suporte" -> Icons.Default.SupportAgent
                        else -> Icons.Default.Person
                    }
                    Column(
                        modifier = Modifier.padding(end = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = icone,
                            contentDescription = colab.nivel
                        )
                    }
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.End) {
                            Text(text = "Nome:", fontWeight = FontWeight.Bold)
                            Text(text = "Email:", fontWeight = FontWeight.Bold)
                            Text(text = "Nivel:", fontWeight = FontWeight.Bold)
                        }
                        Column(
                            modifier = Modifier.padding(start = 5.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text("${colab.nome}")
                            Text("${colab.email}")
                            Text("${colab.nivel}")
                        }
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        AcoesListagem(
                            acaoEdit = { acaoEdicao(colab) },
                            acaoDelete = { acaoRemocao(colab) }
                        )
                    }
                }
            }
        }
    }
}