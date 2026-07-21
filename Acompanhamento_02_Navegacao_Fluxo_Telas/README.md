# Acompanhamento 02 - Navegação e Fluxo de Telas

## Informações importantes:

- Disponibilizar no repositório até dia 14/07 (terça-feira) às 13h;
- Não precisa enviar e-mail;
- Favor não consumir APIs, implementar camada de persistência ou utilizar arquitetura MVVM;
- Fique à vontade para utilizar componentes que não foram vistos.

## Características do projeto:

- Ter entre 3 e 5 telas;
- Deverá realizar o uso de parâmetros (primitivos ou type-safety);
- Precisa utilizar o Scaffold.

## Informações do projeto:

Com tema livre, crie uma aplicação que supra as características listadas, abaixo algumas sugestões:

- Aplicativo para loja virtual;
- Aplicação responsável por exibir informações de um evento;
- App para acompanhar alguma competição (futebol, vôlei, basquete, etc);
- Aplicativo de hotel ou parque de diversões.

---

# Definição do projeto

Atendendo aos critêrios estabelecidos nas secções anteriores, estabelecemos o tema do projeto --objeto dessa Atividade de Acompanhamento; como: "App Android para criação de comandas numa pizzaria"

## Objetivos do projeto

- Implementar uma app Android com Kotlin para a criação de comandas numa pizzaria.
- O escopo do projeto abrange:
  * Gerenciar um Catálogo de Categorias
  * Gerenciar um Catálogo de Produtos
  * Gerenciar uma unica Comanda
- Para atender ao escopo do projeto, a app deve contar com as seguintes telas: Cadastro de Categorias, Cadastro de Produtos, Listagem de Categorias, Listagem de Produtos, Informações do Produto, Visualizar Comanda (tela principal).
- O aplicativo deve contar com os seguintes menus e submenus: 
  * Menu Principal
    - Visualizar Comanda (navega para a tela Visualizar Comanda)
    - Separador visual (linha horizontal que separa os submenus em grupos)
    - Submenu Cadastro de Categorias (navega para a tela Cadastro de Categorias)
    - Submenu Cadastro de Produtos (navega para a tela Cadastro de Produtos)
- A navegação entre as telas deve ser: Visualizar Comanda -> Listagem de Categorias -> Listagem de Produtos (filtrado por: Categoria) -> Informações do Produto -> Listagem de Categorias ou retornar para Visualizar Comanda.
- A entidades que irão compor a camada de datos são:
  * Categoria (data class com os campos idCategoria: Int [Identificador Unico], nomeCategoria: String)
  * Produto (data class com os campos idProduto: Int [Identificador Unico], idCategoria: Int, nomeProduto: String, preco: Float)
  * ItemComanda (data class com os campos idProduto: Int, qtdProduto: Int, notaAoChef: String, subTotal: Float)
  * Comanda (data class com os campos listaItemsComanda: List<ItemComanda>, totalComanda: Float)
  * Repositorio (data class com os campos catalogoCategorias: List<Categoria>, catalogoProdutos: List<Produto>, comanda: Comanda)

interface IEntity {
  Int getNewUID();
  Object getRecord(Int uID);
  Object 
}

data class Categoria Categoria (
  idCategoria: Int, 
  nomeCategoria: String, 
)

data class Produto (
  campos idProduto: Int, 
  idCategoria: Int, 
  nomeProduto: String, 
  preco: Float
)

data class ItemComanda {
  idProduto: Int, 
  qtdProduto: Int, 
  notaAoChef: String, 
  subTotal: Float
}

data class Comanda(
  listaItemsComanda: List<ItemComanda>,
  totalComanda: Float
)

class Repositorio(
  catalogoCategorias: List<Categoria>,
  catalogoProdutos: List<Produto>,
  comanda: Comanda
)

## Caracteristicas de cada Tela

- Cadastro de Categorias: a entidade subyascente é Categorias e é fornecido um conjunto . A tela é dividida em dois boxes dispostos verticalmente. O box de cima -chamado boxFormulario; é um formulario para cadastrar novos registros na entidade subyascente. Contem um OutlinedTextField por cada campo da entidade subyascente para o usuário ingresar dados exceptuando no caso do campo do Identificador Unico que deve aparecer visível porém desabilitado. O valor do Identificador Unico é gerado mediante o consumo de uma função de autoGeração provida pela entidade subyascente no objeto Repository. Na parte inferior do formulario estão os botões Cancelar e Salvar, os quais no evento onClick, invocarão as funcões limparFormulario e salvarNovo ou salvarEdicao respectivamente dependendo de se o formulario esta no modo de Edição ou não. O box de baixo -chamado boxListagem; é uma listagem das Categorias cadastradas onde cada elemento da lista é uma fila que apresenta os dados organizados nas colunas labels (Etiquetas para cada campo da entidade), data (valores para cada campo da entidade) e actions (dois botões: a de cima e a de baixo chamam as funcões editarRegistro  chama a função removerRegistro. A tela implementa um scaffold com o botão de ação invocando a função de Cadastrar nova Categoria.
- Cadastro de Produtos, 
- Listagem de Categorias, 
- Listagem de Produtos, 
- Informações do Produto, 
- Visualizar Comanda

