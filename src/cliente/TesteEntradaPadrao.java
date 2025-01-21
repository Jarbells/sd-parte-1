package cliente;

import comum.Pessoa;
import comum.PStream;

import java.io.*;

public class TesteEntradaPadrao {
    public static void main(String[] args) {
        try (PStream pStream = new PStream(System.in)) {
            System.out.println("Digite os dados do fluxo (em formato binário):");
            Pessoa[] pessoas = pStream.receberDados();

            System.out.println("Dados recebidos:");
            for (Pessoa pessoa : pessoas) {
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("CPF: " + pessoa.getCpf());
                System.out.println("Idade: " + pessoa.getIdade());
                System.out.println("-------------------");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
