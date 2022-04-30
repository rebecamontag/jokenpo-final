package client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner input = null;
        Scanner entrada = new Scanner(System.in);
        Socket socket;
        Scanner teclado = null;
        PrintStream output = null;

        System.out.println("Bem vindo ao Jokenpo\n");
        System.out.println("Digite:\n1 - para jogar contra CPU\n2 - para jogar online\n");
        int escolha = Integer.parseInt(entrada.nextLine());

        if (escolha == 1) {
            final String IP = "127.0.0.1";
            final int PORT = 12345;

            // criação do socket e pedido de conexão
            try {
                socket = new Socket(IP, PORT);
            } catch (Exception e) {
                System.out.println("Não foi possivel conectar ao servidor.");
                return;
            }
            // fase de comunicação
            try {
                input = new Scanner(socket.getInputStream());
                output = new PrintStream(socket.getOutputStream());
                teclado = new Scanner(System.in);
                String msg;
                String resultado;
                String escolhaCPU;

                do {
                    System.out.print("\nDigite:\n0 - Pedra\n1 - Papel\n2 - Tesoura\n");
                    msg = teclado.nextLine();
                    output.println(msg);
                    escolhaCPU = input.nextLine();
                    System.out.println(escolhaCPU);
                    resultado = input.nextLine();
                    System.out.println(resultado);
                } while (!msg.equalsIgnoreCase("exit"));

                // System.out.println("Recebido: " + msg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // fase de encerramento a conexão
            try {
                input.close();
                entrada.close();
                output.close();
                teclado.close();
                socket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (escolha == 2) {
            final String IP = "127.0.0.1";
            final int PORT = 11111;

            // criação do socket e pedido de conexão
            try {
                socket = new Socket(IP, PORT);
            } catch (Exception e) {
                System.out.println("Não foi possivel conectar ao servidor.");
                return;
            }
            // fase de comunicação
            try {
                input = new Scanner(socket.getInputStream());
                output = new PrintStream(socket.getOutputStream());
                teclado = new Scanner(System.in);
                String msg;
                String resultado;
                String escolhaCPU;

                do {
                    System.out.print("\nDigite:\n0 - Pedra\n1 - Papel\n2 - Tesoura\n");
                    msg = teclado.nextLine();
                    output.println(msg);
                    escolhaCPU = input.nextLine();
                    System.out.println(escolhaCPU);
                    resultado = input.nextLine();
                    System.out.println(resultado);
                } while (!msg.equalsIgnoreCase("exit"));

                // System.out.println("Recebido: " + msg);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // fase de encerramento a conexão
            try {
                input.close();
                entrada.close();
                output.close();
                teclado.close();
                socket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Escolha inválida");
        }
    }
}
