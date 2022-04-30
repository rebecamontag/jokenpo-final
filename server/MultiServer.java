package server;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class MultiServer {

    public static void main(String[] args) {
        final int PORT = 11111;
        ServerSocket serverSocket;
        Socket clientSocket = null;
        Socket clientSocket2 = null;
        Scanner input = null;
        Scanner input2 = null;
        int empate = 0, vitoria = 0, derrota = 0;
        int empate2 = 0, vitoria2 = 0, derrota2 = 0;
        PrintStream output = null;
        PrintStream output2 = null;

        // criar o socket e fazer o bind
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (Exception e) {
            System.out.println("Porta " + PORT + " já está em uso.");
            return;
        }

        // aguardar um pedido de conexão
        try {
            System.out.println("Multiplayer Server");
            System.out.println("Aguardando jogador 1...");
            clientSocket = serverSocket.accept();
            System.out.println("Conectado com jogador 1 " + clientSocket.getInetAddress().getHostAddress());
            System.out.println("Aguardando jogador 2...");
            clientSocket2 = serverSocket.accept();
            System.out.println("Conectado com jogador 2 " + clientSocket2.getInetAddress().getHostAddress());
        } catch (Exception e) {
            System.out.println("Erro na conexão.");
            System.out.println(e.getMessage());
        }

        // fase de comunicação
        try {
            input = new Scanner(clientSocket.getInputStream());
            input2 = new Scanner(clientSocket2.getInputStream());
            output = new PrintStream(clientSocket.getOutputStream());
            output2 = new PrintStream(clientSocket2.getOutputStream());
            String msg;
            String msg2;

            Boolean jogar=true;
            while(jogar) {
                msg = input.nextLine();
                System.out.println("Escolha do jogador 1: " + msg);
                if (msg.equalsIgnoreCase("exit")) jogar=false;
                // if (msg.equalsIgnoreCase("exit")) break;
                msg2 = input2.nextLine();
                if (msg2.equalsIgnoreCase("exit")) jogar=false;
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
            // } while (!msg.equalsIgnoreCase("exit") || !msg2.equalsIgnoreCase("exit"));

            // output.println("Boa noite cliente!");
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
            clientSocket.close();
            serverSocket.close();
            output.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
