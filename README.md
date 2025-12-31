# Jogo da Velha

Este projeto consiste em uma implementação do clássico jogo "Jogo da Velha" (Tic-Tac-Toe) em Java. Ele foi desenvolvido como um trabalho acadêmico para a disciplina de **Testes de Software**, com o objetivo de aplicar conceitos de desenvolvimento orientado a testes e garantia de qualidade de código.

## Descrição do Projeto

O sistema permite que dois jogadores joguem alternadamente em um tabuleiro 3x3. O jogo valida as jogadas, verifica automaticamente as condições de vitória ou empate e impede jogadas inválidas.

## Critérios Atendidos

O projeto foi desenvolvido para atender aos seguintes critérios e requisitos funcionais, validados através de testes unitários:

1.  **Gerenciamento de Tabuleiro**: O jogo mantém o estado de um tabuleiro 3x3.
2.  **Validação de Jogadas**:
    *   Aceita apenas posições válidas (1 a 9).
    *   Rejeita jogadas em posições fora dos limites (ex: 0 ou 10).
    *   Rejeita jogadas em células já ocupadas.
    *   Rejeita jogadas após o término da partida.
3.  **Controle de Turnos**: Alternância automática entre os jogadores 'X' e 'O'.
4.  **Condições de Vitória**: Detecção automática de vitória para:
    *   Linhas horizontais.
    *   Colunas verticais.
    *   Diagonais.
5.  **Condição de Empate**: Detecção de empate (velha) quando todas as posições são preenchidas sem um vencedor.
6.  **Cobertura de Testes**: O projeto inclui uma suíte de testes unitários (JUnit) cobrindo diversos cenários de teste (Casos de Teste - CT), garantindo a robustez da lógica implementada.

## Tecnologias Utilizadas

*   **Java**: Linguagem de programação utilizada.
*   **JUnit**: Framework para testes unitários.
*   **Maven**: Gerenciador de dependências e build.

## Como Executar os Testes

Para executar os testes unitários e verificar a conformidade com os critérios, utilize o comando Maven:

```bash
mvn test
```
