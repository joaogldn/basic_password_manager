import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        GerenciadorSenhas gerenciador = new GerenciadorSenhas();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Gerenciador de Senhas Seguro ===");

        try {
            while (true) {
                System.out.println("\nEscolha uma opção:");
                System.out.println("1 - Cadastrar nova senha");
                System.out.println("2 - Listar senhas cadastradas");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");

                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        System.out.print("Digite o nome do serviço: ");
                        String servico = scanner.nextLine();

                        System.out.print("Digite o nome do usuário: ");
                        String usuario = scanner.nextLine();

                        System.out.print("Digite a senha: ");
                        String senha = scanner.nextLine();

                        gerenciador.cadastrarSenha(servico, usuario, senha);
                        break;

                    case "2":
                        gerenciador.listarSenhas();
                        break;

                    case "0":
                        System.out.println("Saindo...");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
