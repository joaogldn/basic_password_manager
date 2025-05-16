import java.io.*;
import java.net.*;
import java.security.*;

public class VerificadorViolacao {
    public static void verificarSenha(String senha) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = md.digest(senha.getBytes("UTF-8"));
        String hashCompleto = bytesParaHex(hashBytes).toUpperCase();
        String prefixo = hashCompleto.substring(0, 5);
        String sufixo = hashCompleto.substring(5);

        URL url = new URL("https://api.pwnedpasswords.com/range/" + prefixo);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        String linha;
        while ((linha = leitor.readLine()) != null) {
            if (linha.startsWith(sufixo)) {
                System.out.println("⚠️  Senha encontrada em vazamento!");
                return;
            }
        }
        System.out.println("✅ Senha não encontrada em vazamentos.");
    }

    private static String bytesParaHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
