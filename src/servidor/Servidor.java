package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import comum.Request;
import comum.Response;

public class Servidor {
    public static void main(String[] args) {
        int porta = 12345;

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor aguardando conexões an porta " + porta);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {

                    System.out.println("Cliente conectado: " + socket.getInetAddress());

                    // Receber requisição
                    Request request = (Request) input.readObject();
                    System.out.println("Operação recebida: " + request.getOperacao());
                    System.out.println("Dados recebidos: " + request.getDados());

                    // Processar requisição e criar resposta
                    String resultado = "Resposta para operação " + request.getOperacao();
                    Response response = new Response("OK", resultado);

                    // Enviar resposta
                    output.writeObject(response);
                    System.out.println("Resposta enviada ao cliente.");
                } catch (Exception e) {
                    System.err.println("Erro ao processar cliente: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
