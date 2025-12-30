package org.example;

import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        JogoDaVelha jogo = new JogoDaVelha();

        System.out.println("=== JOGO DA VELHA ===");
        System.out.println("Use as posições de 1 a 9 para jogar (como um teclado numérico):");
        System.out.println(" 1 | 2 | 3 ");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println(" 7 | 8 | 9 ");
        System.out.println();

        while (!jogo.isJogoFinalizado()) {
            System.out.println(jogo.getTabuleiroVisual());
            System.out.println("Turno do jogador: " + jogo.getJogadorAtual());
            System.out.print("Digite a posição (1-9): ");

            if (scanner.hasNextInt()) {
                int posicao = scanner.nextInt();
                String resultado = jogo.realizarJogada(posicao);
                System.out.println(">> " + resultado);
            } else {
                System.out.println(">> Entrada inválida! Digite um número.");
                scanner.next(); // Limpa o buffer
            }
            System.out.println("-----------------------------");
        }

        System.out.println(jogo.getTabuleiroVisual());
        System.out.println("FIM DE JOGO!");
        if (jogo.getVencedor() != null) {
            System.out.println("Vencedor: " + jogo.getVencedor());
        } else {
            System.out.println("Deu Velha (Empate)!");
        }

        scanner.close();

    }
}
