import java.util.List;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorSenhas gerenciador = new GerenciadorSenhas();

        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar nova senha");
            System.out.println("2 - Listar senhas cadastradas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            String escolha = scanner.nextLine();

            switch (escolha) {
                case "1":
                    System.out.print("Informe o nome do serviço: ");
                    String servico = scanner.nextLine().trim();

                    System.out.print("Informe o email: ");
                    String email = scanner.nextLine().trim();

                    System.out.print("Deseja gerar uma senha segura automaticamente? (s/n): ");
                    String opcao = scanner.nextLine().trim().toLowerCase();
                    String senha;

                    if (opcao.equals("s")) {
                        senha = GeradorSenha.gerarSenhaForte();
                        System.out.println("Senha gerada: " + senha);
                    } else {
                        System.out.print("Informe a senha: ");
                        senha = scanner.nextLine().trim();
                    }

                    if (VerificadorViolacao.verificarVazamento(senha)) {
                        System.out.println("⚠️ Atenção: Esta senha já apareceu em vazamentos anteriores!");
                    }

                    String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());
                    gerenciador.adicionarSenha(new Senha(servico, email, senhaCriptografada));
                    System.out.println("✅ Senha cadastrada com sucesso!");
                    break;

                case "2":
                    List<Senha> senhas = gerenciador.listarSenhas();
                    if (senhas.isEmpty()) {
                        System.out.println("Nenhuma senha cadastrada.");
                    } else {
                        for (Senha s : senhas) {
                            System.out.println("🔐 Serviço: " + s.getServico());
                            System.out.println("📧 Email: " + s.getEmail());
                            System.out.println("Senha (criptografada): " + s.getSenhaCriptografada());
                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case "0":
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
