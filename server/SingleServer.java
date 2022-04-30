package server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class SingleServer {

    public static void main(String[] args) {
        final int PORT = 12345;
        ServerSocket serverSocket;
        Socket clientSocket = null;
        Scanner input = null;
        int empate = 0, vitoria = 0, derrota = 0;
        PrintStream output = null;

        // criar o socket e fazer o bind
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (Exception e) {
            System.out.println("Porta " + PORT + " já está em uso.");
            return;
        }

        // aguardar um pedido de conexão
        try {
            System.out.println("Singleplayer Server");
            System.out.println("Aguardando pedido de conexão...");
            clientSocket = serverSocket.accept();
            System.out.println("Conectado com " + clientSocket.getInetAddress().getHostAddress());
        } catch (Exception e) {
            System.out.println("Erro na conexão.");
            System.out.println(e.getMessage());
        }

        // fase de comunicação
        try {
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintStream(clientSocket.getOutputStream());
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

            // output.println("Boa noite cliente!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // fase de encerrar a conexão
        try {
            output.println("(RESULTADO FINAL)"+ "\n" + "Vitórias: " + vitoria + " | " + "Derrotas: " + derrota + " | "
            + "Empates: " + empate);
            input.close();
            clientSocket.close();
            serverSocket.close();
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
