
import org.example.JogoDaVelha;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class JogoDaVelhaTest {

    private JogoDaVelha jogo;

    @Before
    public void setUp() {
        jogo = new JogoDaVelha();
    }

    // --- CT01: Jogada válida (Centro) ---
    @Test
    public void testCT01_JogadaValida() {
        String resultado = jogo.realizarJogada(5);

        assertEquals("Jogada aceita", resultado);
        assertEquals("X", jogo.getPosicao(5));
        assertEquals("O", jogo.getJogadorAtual());
    }

    // --- CT02: Limite inválido (0) ---
    @Test
    public void testCT02_PosicaoInvalidaZero() {
        String resultado = jogo.realizarJogada(0);
        assertEquals("Jogada rejeitada: Posição inválida", resultado);
    }

    // --- CT03: Limite inválido (10) ---
    @Test
    public void testCT03_PosicaoInvalidaDez() {
        String resultado = jogo.realizarJogada(10);
        assertEquals("Jogada rejeitada: Posição inválida", resultado);
    }

    // --- CT04: Célula ocupada ---
    @Test
    public void testCT04_CelulaOcupada() {
        jogo.realizarJogada(5); // X joga
        String resultado = jogo.realizarJogada(5); // O tenta jogar na mesma

        assertEquals("Jogada rejeitada: Célula ocupada", resultado);
        assertEquals("X", jogo.getPosicao(5)); // Garante que não mudou
        assertEquals("O", jogo.getJogadorAtual()); // Turno não deve mudar se erro
    }

    // --- CT05: Símbolo Inválido ---
    @Test
    public void testCT05_SimboloInvalido() {
        assertEquals("X", jogo.getJogadorAtual());
        jogo.realizarJogada(1);
        assertEquals("O", jogo.getJogadorAtual());
        assertNotEquals("A", jogo.getJogadorAtual());
    }

    // --- CT06: Jogo Finalizado ---
    @Test
    public void testCT06_JogoFinalizadoTentaJogar() {
        jogo.realizarJogada(1); // X
        jogo.realizarJogada(4); // O
        jogo.realizarJogada(2); // X
        jogo.realizarJogada(5); // O
        jogo.realizarJogada(3); // X vence

        assertTrue(jogo.isJogoFinalizado());

        String resultado = jogo.realizarJogada(9);
        assertEquals("Jogada rejeitada: Jogo finalizado", resultado);
    }

    // --- CT08: Vitória de X (Linha) ---
    @Test
    public void testCT08_VitoriaLinhaX() {
        jogo.realizarJogada(1); // X
        jogo.realizarJogada(4); // O
        jogo.realizarJogada(2); // X
        jogo.realizarJogada(5); // O
        String resultado = jogo.realizarJogada(3); // X fecha linha 1-2-3

        assertEquals("Vitória de X", resultado);
        assertTrue(jogo.isJogoFinalizado());
        assertEquals("X", jogo.getVencedor());
    }

    // --- CT09: Vitória de O (Coluna) ---
    @Test
    public void testCT09_VitoriaColunaO() {
        jogo.realizarJogada(1); // X
        jogo.realizarJogada(2); // O
        jogo.realizarJogada(4); // X
        jogo.realizarJogada(5); // O
        jogo.realizarJogada(9); // X
        String resultado = jogo.realizarJogada(8); // O fecha coluna 2-5-8

        assertEquals("Vitória de O", resultado);
        assertEquals("O", jogo.getVencedor());
    }

    // --- CT10: Vitória Diagonal ---
    @Test
    public void testCT10_VitoriaDiagonal() {
        jogo.realizarJogada(1); // X
        jogo.realizarJogada(2); // O
        jogo.realizarJogada(5); // X (meio)
        jogo.realizarJogada(3); // O
        String resultado = jogo.realizarJogada(9); // X fecha 1-5-9

        assertEquals("Vitória de X", resultado);
    }

    // --- CT12: Empate (Velha) ---
    @Test
    public void testCT12_Empate() {
        // Sequência para dar velha
        jogo.realizarJogada(1); // X
        jogo.realizarJogada(2); // O
        jogo.realizarJogada(3); // X
        jogo.realizarJogada(5); // O
        jogo.realizarJogada(4); // X
        jogo.realizarJogada(6); // O
        jogo.realizarJogada(8); // X
        jogo.realizarJogada(7); // O
        String resultado = jogo.realizarJogada(9); // X

        assertEquals("Empate", resultado);
        assertTrue(jogo.isJogoFinalizado());
        assertNull(jogo.getVencedor());
    }

    // --- CT14: Coordenada Válida (Mapeamento) ---
    @Test
    public void testCT14_CoordenadaValidaMapeada() {
        // Simula input (2,3) -> Posição 6
        String resultado = jogo.realizarJogada(6);
        assertEquals("Jogada aceita", resultado);
        assertEquals("X", jogo.getPosicao(6));
    }

    // --- CT17: Valor Limite Inferior (1) ---
    @Test
    public void testCT17_LimiteInferiorValido() {
        String resultado = jogo.realizarJogada(1);
        assertEquals("Jogada aceita", resultado);
    }

    // --- CT18: Valor Limite Superior (9) ---
    @Test
    public void testCT18_LimiteSuperiorValido() {
        String resultado = jogo.realizarJogada(9);
        assertEquals("Jogada aceita", resultado);
    }

    // --- CT19: Valor Limite Inválido (-1) ---
    @Test
    public void testCT19_LimiteInvalidoNegativo() {
        String resultado = jogo.realizarJogada(-1);
        assertEquals("Jogada rejeitada: Posição inválida", resultado);
    }
    
    // ====================================================================
    // TESTES DE FLUXO DE DADOS - CRITÉRIO: TODOS OS DU-CAMINHOS
    // ====================================================================

    /**
     * CT-DU-01: Valida o P-Uso da variável 'posicao' (Linha 1 -> 6 -> 7)
     * Caminho onde a validação de limite falha.
     */
    @Test
    public void testCT_DU_01_PosicaoInvalida() {
        String resultado = jogo.realizarJogada(0);
        
        assertEquals("Jogada rejeitada: Posição inválida", resultado);
    }

    /**
     * CT-DU-02: Valida o C-Uso da variável 'posicao' (Linha 1 -> 6 -> 10)
     * Caminho de sucesso até o cálculo dos índices.
     */
    @Test
    public void testCT_DU_02_CalculoIndices() {
        String resultado = jogo.realizarJogada(5);
        
        assertEquals("Jogada aceita", resultado);
    }

    /**
     * CT-DU-03: Valida o P-Uso das variáveis 'linha'/'coluna' (Linha 10 -> 13 -> 14)
     * Caminho onde os índices calculados apontam para uma célula ocupada.
     */
    @Test
    public void testCT_DU_03_CelulaOcupada() {
        jogo.realizarJogada(5); 

        String resultado = jogo.realizarJogada(5);
        
        assertEquals("Jogada rejeitada: Célula ocupada", resultado);
    }

    /**
     * CT-DU-04: Valida o C-Uso das variáveis 'linha'/'coluna' (Linha 10 -> 13 -> 17)
     * Caminho onde os índices são usados para atualizar a matriz.
     */
    @Test
    public void testCT_DU_04_AtualizacaoMatriz() {
        jogo.realizarJogada(5);
        
        assertEquals("X", jogo.getPosicao(5));
    }

    /**
     * CT-DU-05: Valida o C-Uso da variável 'tabuleiro' (Linha 17 -> 20 -> 21)
     * Caminho onde a atualização do tabuleiro leva a uma vitória.
     */
    @Test
    public void testCT_DU_05_FluxoVitoria() {
        jogo.realizarJogada(1);
        jogo.realizarJogada(4);
        jogo.realizarJogada(2);
        jogo.realizarJogada(5);

        String resultado = jogo.realizarJogada(3); 
        
        assertEquals("Vitória de X", resultado);
        assertTrue(jogo.isJogoFinalizado());
    }

    /**
     * CT-DU-06: Valida o P-Uso da variável 'jogadasRealizadas' (Linha 18 -> 24 -> 25)
     * Caminho onde o contador define o empate.
     */
    @Test
    public void testCT_DU_06_FluxoEmpate() {
        jogo.realizarJogada(1); // X
        jogo.realizarJogada(2); // O
        jogo.realizarJogada(3); // X
        jogo.realizarJogada(5); // O
        jogo.realizarJogada(4); // X
        jogo.realizarJogada(6); // O
        jogo.realizarJogada(8); // X
        jogo.realizarJogada(7); // O

        String resultado = jogo.realizarJogada(9); 
        
        assertEquals("Empate", resultado);
        assertTrue(jogo.isJogoFinalizado());
    }

    /**
     * CT-DU-07: Valida o Uso da variável 'jogadorAtual' após alternância (Linha 29 -> Próx L17)
     * Verifica se a definição em um turno afeta corretamente o próximo.
     */
    @Test
    public void testCT_DU_07_AlternanciaJogador() {
        jogo.realizarJogada(1); 
        
        jogo.realizarJogada(2);
        
        assertEquals("O", jogo.getPosicao(2));
        assertEquals("X", jogo.getJogadorAtual()); // Preparado para o próximo (X)
    }
}
