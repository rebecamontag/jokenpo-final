package V2.client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        final String IP = "127.0.0.1";
        final int PORT = 9876;
        Socket socket;
        Scanner teclado = null;
        Scanner entrada = new Scanner(System.in);
        Scanner input = null;
        PrintStream output = null;

        System.out.println("Bem vindo ao Jokenpo\n");
        // System.out.println("Digite:\n1 - para jogar contra CPU\n2 - para jogar
        // online\n");
        // int escolha = Integer.parseInt(entrada.nextLine());

        // criação do socket e pedido de conexão
        try {
            socket = new Socket(IP, PORT);
        } catch (Exception e) {
            System.out.println("Não foi possivel conectar ao servidor.");
            entrada.close();
            return;
        }

        // fase de escolha do modo de jogo
        try {
            output = new PrintStream(socket.getOutputStream());
            System.out.println("Digite:\n1 - para jogar contra CPU\n2 - para jogar online\n");
            String escolha = entrada.nextLine();
            output.println(escolha);
        } catch (Exception e) {
            System.out.println(e);
        }

        // fase de comunicação
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            teclado = new Scanner(System.in);
            // Escuta escuta = new Escuta(socket);
            // escuta.start();

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

            // escuta.parar();

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

    }

}
