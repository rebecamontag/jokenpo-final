package V2.server;

import java.io.PrintStream;
import java.net.Socket;
// import java.util.Random;
import java.util.Scanner;

public class Multiplayer extends Thread {

    private Socket cliente;
    private Socket cliente2;
    private Scanner input = null;
    private Scanner input2 = null;
    private PrintStream output = null;
    private PrintStream output2 = null;
    int empate = 0, vitoria = 0, derrota = 0;
    int empate2 = 0, vitoria2 = 0, derrota2 = 0;

    public Multiplayer(Socket socket, Socket socket2) {
        this.cliente = socket;
        this.cliente2 = socket2;
    }

    @Override
    public void run() {
        try {
            input = new Scanner(cliente.getInputStream());
            input2 = new Scanner(cliente2.getInputStream());
            output = new PrintStream(cliente.getOutputStream());
            output2 = new PrintStream(cliente2.getOutputStream());
            String msg;
            String msg2;

            Boolean jogar = true;
            while (jogar) {
                msg = input.nextLine();
                System.out.println("Escolha do jogador 1: " + msg);
                if (msg.equalsIgnoreCase("exit"))
                    jogar = false;
                // if (msg.equalsIgnoreCase("exit")) break;
                msg2 = input2.nextLine();
                if (msg2.equalsIgnoreCase("exit"))
                    jogar = false;
                // if (msg2.equalsIgnoreCase("exit")) break;
                System.out.println("Escolha do jogador 2: " + msg2);

                // lógica do jogo
                try {
                    int escolhaJogador = Integer.parseInt(msg);
                    int escolhaJogador2 = Integer.parseInt(msg2);

                    if (escolhaJogador == escolhaJogador2) {
                        System.out.print("Empate");
                        output.println("Escolha do jogador 2: " + escolhaJogador2 + "\n" + "Empate");
                        output2.println("Escolha do jogador 1: " + escolhaJogador + "\n" + "Empate");
                        empate++;
                        empate2++;
                    } else if (escolhaJogador == 0 && escolhaJogador2 == 2) {
                        System.out.print("Vitória do jogador 1");
                        output.println("Escolha do jogador 2: " + escolhaJogador2 + "\n" + "Você venceu!");
                        output2.println("Escolha do jogador 1: " + escolhaJogador + "\n" + "Você perdeu!");
                        vitoria++;
                        derrota2++;
                    } else if (escolhaJogador == 2 && escolhaJogador2 == 0) {
                        System.out.print("Vitória do jogador 2");
                        output.println("Escolha do jogador 2: " + escolhaJogador2 + "\n" + "Você perdeu!");
                        output2.println("Escolha do jogador 1: " + escolhaJogador + "\n" + "Você venceu!");
                        vitoria2++;
                        derrota++;
                    } else if (escolhaJogador == 0 && escolhaJogador2 == 1) {
                        System.out.print("Vitória do jogador 2");
                        output.println("Escolha do jogador 2: " + escolhaJogador2 + "\n" + "Você perdeu!");
                        output2.println("Escolha do jogador 1: " + escolhaJogador + "\n" + "Você venceu!");
                        vitoria2++;
                        derrota++;
                    } else if (escolhaJogador == 1 && escolhaJogador2 == 0) {
                        System.out.print("Vitória do jogador 2");
                        output.println("Escolha do jogador 2: " + escolhaJogador2 + "\n" + "Você venceu!");
                        output2.println("Escolha do jogador 1: " + escolhaJogador + "\n" + "Você perdeu!");
                        vitoria++;
                        derrota2++;
                    } else if (escolhaJogador == 2 && escolhaJogador2 == 1) {
                        System.out.print("Vitória do jogador 2");
                        output.println("Escolha do jogador 2: " + escolhaJogador2 + "\n" + "Você venceu!");
                        output2.println("Escolha do jogador 1: " + escolhaJogador + "\n" + "Você perdeu!");
                        vitoria++;
                        derrota2++;
                    } else if (escolhaJogador == 1 && escolhaJogador2 == 2) {
                        System.out.print("Vitória do jogador 2");
                        output.println("Escolha do jogador 2: " + escolhaJogador2 + "\n" + "Você perdeu!");
                        output2.println("Escolha do jogador 1: " + escolhaJogador + "\n" + "Você venceu!");
                        vitoria2++;
                        derrota++;
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // fase de encerrar a conexão
        try {
            output.println("(RESULTADO FINAL) " + "Vitórias: " + vitoria + " | " + "Derrotas: " + derrota + " | "
                    + "Empates: " + empate);
            output2.println("(RESULTADO FINAL) " + "Vitórias: " + vitoria2 + " | " + "Derrotas: " + derrota2 + " | "
                    + "Empates: " + empate2);
            input.close();
            input2.close();
            cliente.close();
            cliente2.close();
            output.close();
            output2.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
