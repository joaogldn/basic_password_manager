import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;
import java.lang.reflect.Type;

public class GerenciadorSenhas {
    private List<Senha> senhas;
    private static final String ARQUIVO = "senhas.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public GerenciadorSenhas() {
        this.senhas = carregarSenhas();
    }

    public void adicionarSenha(Senha senha) {
        senhas.add(senha);
        salvarSenhas();
    }

    public List<Senha> listarSenhas() {
        return senhas;
    }

    private void salvarSenhas() {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(senhas, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar senhas: " + e.getMessage());
        }
    }

    private List<Senha> carregarSenhas() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            Type tipoLista = new TypeToken<List<Senha>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
