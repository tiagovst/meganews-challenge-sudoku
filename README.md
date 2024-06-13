# Desafio de Programação: Solucionador de Sudoku com Interface Gráfica

O presente projeto foi desenvolvido por Tiago Santana como requisito da fase Desafio para a vaga de <b>Desenvolvedor Back-End Júnior<b> na empresa <b>MegaNews<b>.

Como requisitado, este programa possibilita, de forma simplificada, a solução de uma matriz de Sudoku.

## Desenvolvimento

Durante o desenvolvimento do Aplicativo, diversas decisões consideradas cabíveis para o contexto foram tomadas a fim de 
simplificar a usabilidade e entendimento do código e do projeto em geral.

### Tecnologias utilizadas

O programa foi construído com:
- <b>Java 17<b>
- <b>JavaFX<b> - Plataforma OpenSource que permite a criação de Aplicações com interfaces ricas, em Java/Kotlin.
- <b>CSS<b> - Linguagem para marcação de estilos.
- <b>Maven<b> - Gerenciador do projeto.

### Por que essas tecnologias foram adotadas?

- A linguagem Java foi utilizada devido a familiaridade com suas estruturas e sintaxe. Além disso, ela também possibilita
o desenvolvimento em plataforma independente, com grande comunidade ativa e bibliotecas ricas. 
- A biblioteca JavaFX foi escolhida, pois, através dela (juntamente com a ferramenta Scene Builder), é possível
criar aplicações do lado do cliente de forma rica e com vasta personalizações, permitindo um visual mais agradável.
- O CSS foi escolhido a fim de complementar a biblioteca citada anteriormente, no intuito de melhor organizar e
configurar o estilo da aplicação.
- Usou-se o Maven no projeto por ser uma sólida ferramenta de gerenciamento e de fácil compreensão.

## Funcionamento

- Após a matriz inicial ser preenchida da forma correta, o sistema analisa a Grid de forma que, através de tentativa e erro,
é possível preencher as posições restantes, seguindo as regras do jogo Suduko.
- Por fim, após resolver as posições restantes, o sistema retorna a matriz completa para o usuário exibindo-a na tela.

## Como executar e testar o programa:

Após instalar o JavaFX JDK 17 e o Maven 3.11.0, basta seguir os passos abaixo:

1. Clonar o Repositório
2. Compilar o Projeto:
3. Navegando até o diretório do projeto, execute os seguintes comandos no terminal:
    ```bash
    mvn clean compile
    ```
4. Executar a Aplicação:
    ```bash
   mvn exec:java
    ```

``
Em máquinas com windows a parte inicial do comando pode ser mvnw
``

De forma simplificada, há também a possibilidade de abrir o projeto clonado em um IDE com suporte às tecnologias utilizadas.
(Intellij IDEA, NetBeans são exemplos).

