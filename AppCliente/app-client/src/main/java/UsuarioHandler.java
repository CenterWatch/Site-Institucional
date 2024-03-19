import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioHandler {
    Scanner leitor = new Scanner(System.in);
    List<Usuario> usuariosCadastrados = new ArrayList<>();

    public Boolean verificarUsuarioExiste(String email) {
        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            Usuario usuarioAtual = usuariosCadastrados.get(i);
            if (usuarioAtual.email.equals(email)) {
                return true;
            }
        }

        return false;
    }

    public Boolean cadastrar() {


        System.out.printf("E-mail: ");
        String email = leitor.next();

        if (verificarUsuarioExiste(email)) {
            System.out.println("E-mail já existente.");
            return false;
        }

        System.out.printf("Senha: ");
        String senha = leitor.next();

        if (senha.length() < 8) {
            System.out.println("A senha deve conter mais de 8 caracteres!");
            return false;
        }

        System.out.printf("Nome: ");
        String nome = leitor.next();

        System.out.printf("Sobrenome: ");
        String sobrenome = leitor.next();

        System.out.printf("Telefone: ");
        String tel = leitor.next();

        System.out.printf("CPF: ");
        String cpf = leitor.next();

        System.out.printf("Cargo: ");
        String cargo = leitor.next();

        Usuario cadastro = new Usuario(nome, sobrenome, tel, email, senha, cpf, cargo);

        usuariosCadastrados.add(cadastro);
        System.out.println("\nCadastro realizado com sucesso!");
        return true;
    }

    public void usuarioLogado(Integer i) {
        Boolean logado = true;
        Usuario usuarioLogado = usuariosCadastrados.get(i);

        String nome = usuarioLogado.nome;
        String cargo = usuarioLogado.cargo;

        do {
            // O usuário está logado
            System.out.println("Olá, %s".formatted(nome));

            String opcoes = "\n1. Ver perfil";

            if (cargo.equals("Supervisor"))
                opcoes += "\n2. Ver usuários cadastrados";

            if (cargo.equals("Gerente"))
                opcoes += "\n2. Ver usuários cadastrados\n3. Cadastrar usuários";

            opcoes += "\n0. Sair\n";

            // Supervisores podem somente ver usuários que cadastrou, enquanto Gerentes podem cadastrar novos usuários.

            System.out.println(opcoes);

            switch (leitor.nextInt()) {
                case 0:
                    // Fez logout
                    logado = false;
                    break;
                case 1:
                    usuarioLogado.exibirPerfil();
                    break;
                case 2:
                    if (cargo.equals("Supervisor") || cargo.equals("Gerente"))
                        exibirUsuarios();
                    break;

                case 3:
                    if (cargo.equals("Gerente"))
                        cadastrar();
                    break;
            }

        } while (logado);

        // Ao fazer logout, o usuário é redirecionado para o inicio
        Main.inicio();
    }

    public void entrar() {

        System.out.print("E-mail: ");
        String user = leitor.next();
        System.out.print("Senha: ");
        String pwd = leitor.next();

        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            Usuario usuarioAtual = usuariosCadastrados.get(i);
            if (usuarioAtual.email.equals(user) && usuarioAtual.senha.equals(pwd)) {
                // ENTROU COM SUCESSO
                System.out.println("\nLogin efetuado com sucesso! Redirecionando para seu perfil...\n");
                usuarioLogado(i);
                return;
            }
        }

        System.out.println("Login inválido, tente novamente.");
        entrar();
    }


    void exibirUsuarios() {
        System.out.println("--------------------------");
        System.out.println("USUÁRIOS");

        for(int i =0; i < usuariosCadastrados.size(); i++) {

            Usuario usuario = usuariosCadastrados.get(i);

            System.out.println("--------------------------");
            usuario.exibirPerfil();
        }
        System.out.println("--------------------------\n");
    }

}
