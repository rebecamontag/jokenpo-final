package V2.server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class Singleplayer extends Thread {

    private Socket cliente;
    private Scanner input = null;
    private PrintStream output = null;
    int empate = 0, vitoria = 0, derrota = 0;

    public Singleplayer(Socket socket) {
        this.cliente = socket;
    }

    @Override
    public void run() {
        try {
            input = new Scanner(cliente.getInputStream());
            output = new PrintStream(cliente.getOutputStream());

            String msg;
            do {
                msg = input.nextLine();
                System.out.println("Escolha do jogador: " + msg);

                // lógica do jogo
                try {
                    int escolhaJogador = Integer.parseInt(msg);
                    Random random = new Random();
                    int escolhaCPU = random.nextInt(3);
                    System.out.println("Escolha do computador: " + escolhaCPU);

                    if (escolhaJogador == escolhaCPU) {
                        System.out.print("Empate");
                        output.println("Escolha do computador: " + escolhaCPU + "\n" + "Empate");
                        empate++;
                    } else {
                        switch (escolhaJogador) {
                            case 0:
                                if (escolhaCPU == 2) {
                                    System.out.print("Jogador venceu!");
                                    output.println("Escolha do computador: " + escolhaCPU + "\n" + "Jogador venceu!");
                                    vitoria++;
                                } else {
                                    System.out.print("Computador venceu!");
                                    output.println(
                                            "Escolha do computador: " + escolhaCPU + "\n" + "Computador venceu!");
                                    derrota++;
                                }
                                break;
                            case 2:
                                if (escolhaCPU == 1) {
                                    System.out.print("Jogador venceu!");
                                    output.println("Escolha do computador: " + escolhaCPU + "\n" + "Jogador venceu!");
                                    vitoria++;
                                } else {
                                    System.out.print("Computador venceu!");
                                    output.println(
                                            "Escolha do computador: " + escolhaCPU + "\n" + "Computador venceu!");
                                    derrota++;
                                }
                                break;
                            case 1:
                                if (escolhaCPU == 0) {
                                    System.out.print("Jogador venceu!");
                                    output.println("Escolha do computador: " + escolhaCPU + "\n" + "Jogador venceu!");
                                    vitoria++;
                                } else {
                                    System.out.print("Computador venceu!");
                                    output.println(
                                            "Escolha do computador: " + escolhaCPU + "\n" + "Computador venceu!");
                                    derrota++;
                                }
                                break;
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } while (!msg.equalsIgnoreCase("exit"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // fase de encerrar a conexão
        try {
            output.println("(RESULTADO FINAL): " + "Vitórias: " + vitoria + " | " + "Derrotas: " + derrota + " | "
                    + "Empates: " + empate);
            input.close();
            cliente.close();
            // serverSocket.close();
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
