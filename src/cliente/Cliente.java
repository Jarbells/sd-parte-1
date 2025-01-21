package cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import comum.Request;
import comum.Response;

public class Cliente {
    public static void main(String[] args) {
        String endereco = "localhost";
        int porta = 12345;

        try (Socket socket = new Socket(endereco, porta);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Conectado ao servidor.");

            // Criar e enviar requisição
            Request request = new Request("MENSAGEM DE TESTE", "DADOS DE EXEMPLO");
            output.writeObject(request);
            System.out.println("Requisição enviada ao servidor.");

            // Receber resposta
            Response response = (Response) input.readObject();
            System.out.println("Resposta recebida: ");
            System.out.println("Status: " + response.getStatus());
            System.out.println("Resutado: " + response.getResultado());

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
