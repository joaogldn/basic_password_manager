import java.security.SecureRandom;

public class GeradorSenha {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*";
    private static final SecureRandom random = new SecureRandom();

    public static String gerarSenhaForte() {
        StringBuilder senha = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(CARACTERES.length());
            senha.append(CARACTERES.charAt(index));
        }
        return senha.toString();
    }
}
