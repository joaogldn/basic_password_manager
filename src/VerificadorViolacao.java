import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;

public class VerificadorViolacao {
    private static final String API_URL = "https://api.pwnedpasswords.com/range/";

    public boolean verificarVazamento(String senha) {
        try {
            String hash = sha1(senha).toUpperCase();
            String prefixo = hash.substring(0, 5);
            String sufixo = hash.substring(5);

            URL url = new URL(API_URL + prefixo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            if (status != 200) {
                System.out.println("Erro ao conectar à API de verificação de vazamento: Código HTTP " + status);
                return false;
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String linha;
                while ((linha = in.readLine()) != null) {
                    String[] partes = linha.split(":");
                    if (partes.length >= 2 && partes[0].equalsIgnoreCase(sufixo)) {
                        System.out.println("Senha exposta em vazamentos! Ocorrências: " + partes[1]);
                        return true;
                    }
                }
            }
            return false;

        } catch (Exception e) {
            System.out.println("Erro ao verificar vazamento: " + e.getMessage());
            return false;
        }
    }

    private String sha1(String input) throws Exception {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
        byte[] messageDigest = md.digest(input.getBytes("UTF-8"));

        // Converter bytes para hexadecimal
        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
