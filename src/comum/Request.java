package comum;

import java.io.Serializable;

// Classe Request (empacota mensagem do cliente para o servidor)
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
	private String operacao;
    private String dados;

    public Request(String operacao, String dados) {
        this.operacao = operacao;
        this.dados = dados;
    }

    public String getOperacao() {
        return operacao;
    }

    public String getDados() {
        return dados;
    }
}