package comum;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
	private String status;
    private String resultado;

    public Response(String status, String resultado) {
        this.status = status;
        this.resultado = resultado;
    }

    public String getStatus() {
        return status;
    }

    public String getResultado() {
        return resultado;
    }
}