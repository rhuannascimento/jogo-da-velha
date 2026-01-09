
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
    // IMPLEMENTAÇÃO DO CASO DE TESTE ESTRUTURAL (FLUXO DE DADOS)
    // ====================================================================

    // --- CT-DU-05: Fluxo de Dados Tabuleiro (Def -> Uso em Vitória) ---
    @Test
    public void testCT_DU_05_FluxoVitoria() {
        jogo.realizarJogada(1); // X
        jogo.realizarJogada(4); // O
        jogo.realizarJogada(2); // X
        jogo.realizarJogada(5); // O
        String resultado = jogo.realizarJogada(3);

        assertEquals("Vitória de X", resultado);
        assertTrue(jogo.isJogoFinalizado());
    }

}
