package comum;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PStream extends OutputStream {
    private Pessoa[] pessoas;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    // Construtor para escrita
    public PStream(Pessoa[] pessoas, OutputStream outputStream) {
        this.pessoas = pessoas;
        this.outputStream = outputStream;
        this.dataOutputStream = new DataOutputStream(outputStream);
    }

    // Construtor para leitura
    public PStream(InputStream inputStream) {
        this.dataInputStream = new DataInputStream(inputStream);
    }

    @Override
    public void write(int b) throws IOException {
        if (outputStream != null) {
            outputStream.write(b);
        }
    }

    // Método para enviar dados
    public void enviarDados() throws IOException {
        if (dataOutputStream == null) {
            throw new IllegalStateException("Stream não configurado para envio de dados.");
        }

        dataOutputStream.writeInt(pessoas.length);

        for (Pessoa pessoa : pessoas) {
            byte[] nomeBytes = pessoa.getNome().getBytes("UTF-8");
            dataOutputStream.writeInt(nomeBytes.length);
            dataOutputStream.write(nomeBytes);
            dataOutputStream.writeUTF(pessoa.getCpf());
            dataOutputStream.writeInt(pessoa.getIdade());
        }

        dataOutputStream.flush();
    }

    // Método para receber dados
    public Pessoa[] receberDados() throws IOException {
        if (dataInputStream == null) {
            throw new IllegalStateException("Stream não configurado para leitura de dados.");
        }

        int quantidade = dataInputStream.readInt();
        Pessoa[] pessoas = new Pessoa[quantidade];

        for (int i = 0; i < quantidade; i++) {
            int tamanhoNome = dataInputStream.readInt();
            byte[] nomeBytes = new byte[tamanhoNome];
            dataInputStream.readFully(nomeBytes);
            String nome = new String(nomeBytes, "UTF-8");

            String cpf = dataInputStream.readUTF();
            int idade = dataInputStream.readInt();

            pessoas[i] = new Pessoa(nome, cpf, idade);
        }

        return pessoas;
    }
}
