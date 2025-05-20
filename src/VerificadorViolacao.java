import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class VerificadorViolacao {
    public static boolean verificarVazamento(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(senha.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02X", b));
            }
            String sha1 = sb.toString();
            String prefixo = sha1.substring(0, 5);
            String sufixo = sha1.substring(5);

            URL url = new URL("https://api.pwnedpasswords.com/range/" + prefixo);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String linha;
            while ((linha = in.readLine()) != null) {
                if (linha.startsWith(sufixo)) {
                    return true;
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Erro ao verificar vazamento: " + e.getMessage());
        }
        return false;
    }
}
