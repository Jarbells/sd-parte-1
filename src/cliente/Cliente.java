package cliente;

import comum.Pessoa;
import comum.PStream;

import java.io.*;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        String endereco = "localhost";
        int porta = 12345;

        try (Socket socket = new Socket(endereco, porta)) {
            System.out.println("Conectado ao servidor.");

            try (PStream pStream = new PStream(socket.getInputStream())) {
                Pessoa[] pessoas = pStream.receberDados();

                System.out.println("Dados recebidos do servidor:");
                for (Pessoa pessoa : pessoas) {
                    System.out.println("Nome: " + pessoa.getNome());
                    System.out.println("CPF: " + pessoa.getCpf());
                    System.out.println("Idade: " + pessoa.getIdade());
                    System.out.println("-------------------");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
