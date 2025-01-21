package cliente;

import comum.Pessoa;
import comum.PStream;

import java.io.FileOutputStream;
import java.io.IOException;

public class TesteArquivoCriacao {
    public static void main(String[] args) {
        Pessoa[] pessoas = {
            new Pessoa("Grazy", "123.456.789-00", 19),
            new Pessoa("Jarbas", "987.654.321-00", 40)
        };

        String arquivo = "pessoas.dat";

        try (FileOutputStream fileOutputStream = new FileOutputStream(arquivo);
             PStream pStream = new PStream(pessoas, fileOutputStream)) {

            pStream.enviarDados();
            System.out.println("Arquivo '" + arquivo + "' criado com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
