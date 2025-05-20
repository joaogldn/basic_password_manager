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
            Map<String, Credencial> dados = gson.fromJson(reader, new TypeToken<Map<String, Credencial>>(){}.getType());
            if (dados == null) {
                return new HashMap<>();
            }
            return dados;
        } catch (FileNotFoundException e) {
            // Arquivo não existe ainda - retorna mapa vazio
            return new HashMap<>();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo de senhas: " + e.getMessage());
            return new HashMap<>();
        } catch (JsonSyntaxException e) {
            System.out.println("Erro no formato do arquivo de senhas. O arquivo pode estar corrompido.");
            return new HashMap<>();
        }
    }

    private void salvarSenhas() {
        try (Writer writer = new FileWriter(ARQUIVO_SENHAS)) {
            gson.toJson(senhas, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar as senhas: " + e.getMessage());
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

        try {
            // Hash da senha
            String hash = BCrypt.hashpw(senha, BCrypt.gensalt());

            // Cria credencial e salva
            Credencial cred = new Credencial(usuario, hash);
            senhas.put(servico, cred);
            salvarSenhas();
            System.out.println("Senha cadastrada com sucesso para o serviço: " + servico);
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar a senha: " + e.getMessage());
        }
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
