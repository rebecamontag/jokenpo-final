package V2.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        final int PORT = 9876;
        ServerSocket server;
        Socket client;
        Socket client1 = null;
        Socket client2 = null;
        Scanner input = null;

        try {
            server = new ServerSocket(PORT);
        } catch (Exception e) {
            System.out.println("Porta em uso...");
            return;
        }

        try {
            while (true) {
                System.out.println("Server Jokenpo");
                System.out.println("Aguardando conexão...");
                client = server.accept();

                input = new Scanner(client.getInputStream());
                String msg = input.nextLine();
                int escolha = Integer.parseInt(msg);
                System.out.println("Recebido: " + escolha);

                if (escolha == 1) {
                    Singleplayer single = new Singleplayer(client);
                    single.start();

                }
                if (escolha == 2) {
                    if (client1 == null && client2 == null) {
                        client1 = client;
                        System.out.println("Aguardando outro jogador...");
                    } else if (client1 != null && client2 == null) {
                        client2 = client;
                    }
                    if (client1 != null && client2 != null) {
                        Multiplayer multi = new Multiplayer(client1, client2);
                        multi.start();
                    } else {
                        System.out.println("Aguardando outro jogador...");
                    }

                } else {
                    System.out.println("Escolha inválida");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            server.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
