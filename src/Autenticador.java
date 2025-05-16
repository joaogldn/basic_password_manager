import java.util.Random;

public class Autenticador {
    private String codigoAtual;

    public String enviarCodigo2FA() {
        codigoAtual = String.format("%06d", new Random().nextInt(999999));
        // Aqui você pode integrar com SMS ou email no futuro
        return codigoAtual;
    }

    public boolean verificarCodigo(String codigo) {
        return codigoAtual.equals(codigo);
    }
}
