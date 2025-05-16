import java.io.FileWriter;
import java.io.FileReader;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;
import org.mindrot.jbcrypt.BCrypt;

public class GerenciadorSenhas {
    private Map<String, Usuario> armazenamento;
    private final String arquivo = "senhas.json";

    public GerenciadorSenhas() {
        try {
            armazenamento = new Gson().fromJson(new FileReader(arquivo), new TypeToken<Map<String, Usuario>>(){}.getType());
            if (armazenamento == null) armazenamento = new HashMap<>();
        } catch (Exception e) {
            armazenamento = new HashMap<>();
        }
    }

    public void adicionarCredencial(String servico, String usuario, String senha) {
        String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());
        armazenamento.put(servico, new Usuario(usuario, senhaCriptografada));
        salvar();
        System.out.println("Senha salva com sucesso!");
    }

    public void listarCredenciais() {
        for (String chave : armazenamento.keySet()) {
            System.out.println("Serviço: " + chave + ", Usuário: " + armazenamento.get(chave).getUsuario());
        }
    }

    private void salvar() {
        try (FileWriter writer = new FileWriter(arquivo)) {
            new Gson().toJson(armazenamento, writer);
        } catch (Exception e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }
}
