import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        GerenciadorSenhas gerenciador = new GerenciadorSenhas();
        Autenticador autenticador = new Autenticador();

        System.out.println("Código 2FA enviado: " + autenticador.enviarCodigo2FA());

        System.out.print("Digite o código 2FA: ");
        String codigo = scanner.nextLine();
        if (!autenticador.verificarCodigo(codigo)) {
            System.out.println("Autenticação falhou.");
            return;
        }

        while (true) {
            System.out.println("\n1. Adicionar senha\n2. Listar senhas\n3. Verificar vazamento\n4. Gerar senha forte\n5. Sair");
            String escolha = scanner.nextLine();
            switch (escolha) {
                case "1":
                    System.out.print("Serviço: ");
                    String servico = scanner.nextLine();
                    System.out.print("Usuário: ");
                    String usuario = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    gerenciador.adicionarCredencial(servico, usuario, senha);
                    break;
                case "2":
                    gerenciador.listarCredenciais();
                    break;
                case "3":
                    System.out.print("Senha para verificar: ");
                    String senhaVerificar = scanner.nextLine();
                    VerificadorViolacao.verificarSenha(senhaVerificar);
                    break;
                case "4":
                    System.out.println("Senha sugerida: " + GeradorSenhas.gerarSenhaForte(12));
                    break;
                case "5":
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
