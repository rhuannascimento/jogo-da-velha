package org.example;

public class JogoDaVelha {
    private String[][] tabuleiro;
    private String jogadorAtual;
    private String vencedor;
    private boolean jogoFinalizado;
    private int jogadasRealizadas;

    public JogoDaVelha() {
        reiniciar();
    }

    public void reiniciar() {
        tabuleiro = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = " ";
            }
        }
        jogadorAtual = "X";
        vencedor = null;
        jogoFinalizado = false;
        jogadasRealizadas = 0;
    }

    public String realizarJogada(int posicao) {
        if (jogoFinalizado) {
            return "Jogada rejeitada: Jogo finalizado";
        }

        if (posicao < 1 || posicao > 9) {
            return "Jogada rejeitada: Posição inválida";
        }

        int linha = (posicao - 1) / 3;
        int coluna = (posicao - 1) % 3;

        if (!tabuleiro[linha][coluna].equals(" ")) {
            return "Jogada rejeitada: Célula ocupada";
        }

        tabuleiro[linha][coluna] = jogadorAtual;
        jogadasRealizadas++;

        if (verificarVitoria()) {
            jogoFinalizado = true;
            vencedor = jogadorAtual;
            return "Vitória de " + vencedor;
        } else if (jogadasRealizadas == 9) {
            jogoFinalizado = true;
            return "Empate";
        }

        alternarJogador();
        return "Jogada aceita";
    }

    private void alternarJogador() {
        if (jogadorAtual.equals("X")) {
            jogadorAtual = "O";
        } else {
            jogadorAtual = "X";
        }
    }

    public boolean verificarVitoria() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(tabuleiro[i][0], tabuleiro[i][1], tabuleiro[i][2])) return true;
        }
        for (int j = 0; j < 3; j++) {
            if (checkRowCol(tabuleiro[0][j], tabuleiro[1][j], tabuleiro[2][j])) return true;
        }
        if (checkRowCol(tabuleiro[0][0], tabuleiro[1][1], tabuleiro[2][2])) return true;
        if (checkRowCol(tabuleiro[0][2], tabuleiro[1][1], tabuleiro[2][0])) return true;

        return false;
    }

    private boolean checkRowCol(String c1, String c2, String c3) {
        return !c1.equals(" ") && c1.equals(c2) && c2.equals(c3);
    }

    public String getPosicao(int posicao) {
        int linha = (posicao - 1) / 3;
        int coluna = (posicao - 1) % 3;
        return tabuleiro[linha][coluna];
    }

    public String getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean isJogoFinalizado() {
        return jogoFinalizado;
    }

    public String getVencedor() {
        return vencedor;
    }

    public String getTabuleiroVisual() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(" ").append(tabuleiro[i][0]).append(" | ")
                    .append(tabuleiro[i][1]).append(" | ")
                    .append(tabuleiro[i][2]).append("\n");
            if (i < 2) sb.append("---|---|---\n");
        }
        return sb.toString();
    }
}
