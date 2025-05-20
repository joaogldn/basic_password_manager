import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.mindrot.jbcrypt.BCrypt;

public class GerenciadorSenhas {
    private static final String ARQUIVO_SENHAS = "senhas.json";
    private Map<String, Credencial> senhas;
    private Gson gson;

    public GerenciadorSenhas() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        senhas = carregarSenhas();
    }

    private Map<String, Credencial> carregarSenhas() {
        try (Reader reader = new FileReader(ARQUIVO_SENHAS)) {
            return gson.fromJson(reader, new TypeToken<Map<String, Credencial>>(){}.getType());
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    private void salvarSenhas() {
        try (Writer writer = new FileWriter(ARQUIVO_SENHAS)) {
            gson.toJson(senhas, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar as senhas.");
        }
    }

    public void cadastrarSenha(String servico, String usuario, String senha) {
        // Validação rigorosa de entrada
        if (servico == null || servico.trim().isEmpty()) {
            System.out.println("Erro: Nome do serviço não pode ser vazio.");
            return;
        }
        if (usuario == null || usuario.trim().isEmpty()) {
            System.out.println("Erro: Nome do usuário não pode ser vazio.");
            return;
        }
        if (senha == null || senha.length() < 8) {
            System.out.println("Erro: A senha deve ter pelo menos 8 caracteres.");
            return;
        }

        
        if (!senha.matches("[\\w!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+")) {
            System.out.println("Erro: A senha contém caracteres inválidos.");
            return;
        }

        // Hash da senha
        String hash = BCrypt.hashpw(senha, BCrypt.gensalt());

        // Cria credencial e salva
        Credencial cred = new Credencial(usuario, hash);
        senhas.put(servico, cred);
        salvarSenhas();
        System.out.println("Senha cadastrada com sucesso para o serviço: " + servico);
    }

    public void listarSenhas() {
        if (senhas.isEmpty()) {
            System.out.println("Nenhuma senha cadastrada.");
            return;
        }
        System.out.println("Senhas cadastradas:");
        for (Map.Entry<String, Credencial> entry : senhas.entrySet()) {
            System.out.println("Serviço: " + entry.getKey() + " | Usuário: " + entry.getValue().usuario);
        }
    }

    // Classe interna para armazenar credenciais
    private static class Credencial {
        String usuario;
        String senhaHash;

        Credencial(String usuario, String senhaHash) {
            this.usuario = usuario;
            this.senhaHash = senhaHash;
        }
    }
}
