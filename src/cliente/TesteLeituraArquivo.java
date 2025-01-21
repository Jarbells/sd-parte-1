package cliente;

import comum.Pessoa;
import comum.PStream;

import java.io.FileInputStream;
import java.io.IOException;

public class TesteLeituraArquivo {
    public static void main(String[] args) {
        String arquivo = "pessoas.dat";

        try (FileInputStream fileInputStream = new FileInputStream(arquivo);
             PStream pStream = new PStream(fileInputStream)) {

            Pessoa[] pessoas = pStream.receberDados();

            System.out.println("Dados lidos do arquivo:");
            for (Pessoa pessoa : pessoas) {
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("CPF: " + pessoa.getCpf());
                System.out.println("Idade: " + pessoa.getIdade());
                System.out.println("-------------------");
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
