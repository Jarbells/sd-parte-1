package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import comum.PStream;
import comum.Pessoa;

public class Servidor {
    public static void main(String[] args) {
        int porta = 12345;

        try (ServerSocket servidorSocket = new ServerSocket(porta)) {
            System.out.println("Servidor aguardando conexões na porta " + porta + "...");

            try (Socket clienteSocket = servidorSocket.accept()) {
                System.out.println("Cliente conectado: " + clienteSocket.getInetAddress());

                Pessoa[] pessoas = {
                    new Pessoa("Jarbas", "123.456.789-00", 30),
                    new Pessoa("Grazy", "987.654.321-00", 25)
                };

                try (PStream pStream = new PStream(pessoas, clienteSocket.getOutputStream())) {
                    pStream.enviarDados();
                }

                System.out.println("Dados enviados ao cliente.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
