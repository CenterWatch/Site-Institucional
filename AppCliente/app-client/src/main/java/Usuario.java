import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    Scanner leitor = new Scanner(System.in);
    List<List> usuariosCadastrados = new ArrayList<>();

    Boolean verificarUsuarioExiste(String user) {
        List<String> usuarioAtual;

        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            usuarioAtual = usuariosCadastrados.get(i);
            if (usuarioAtual.get(0).equals(user)) {
                return true;
            }
        }

        return false;
    }

    Boolean cadastrar() {

        System.out.printf("E-mail: ");
        String user = leitor.next();

        System.out.printf("Senha: ");
        String pwd = leitor.next();

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

        if (verificarUsuarioExiste(user)) {
            System.out.println("Usuário já existente.");
            return false;
        }

        if (pwd.length() < 8) {
            System.out.println("A senha deve conter mais de 8 caracteres!");
            return false;
        }

        List<String> cadastroAtual = new ArrayList<>();

        cadastroAtual.add(user);
        cadastroAtual.add(pwd);
        cadastroAtual.add(nome);
        cadastroAtual.add(sobrenome);
        cadastroAtual.add(tel);
        cadastroAtual.add(cpf);
        cadastroAtual.add(cargo);

        usuariosCadastrados.add(cadastroAtual);
        System.out.println("\nCadastro realizado com sucesso! Redirecionando para página de login...");
        return true;
    }

    void usuarioLogado(Integer index) {
        Boolean logado = true;

        List<String> usuario = usuariosCadastrados.get(index);

        /*
        * Usuário, por index:
        * 0 - Email
        * 1 - Senha
        * 2 - Nome
        * 3 - Sobrenome
        * 4 - Telefone
        * 5 - CPF
        * 6 - Cargo
        * */

        String nome = usuario.get(2);
        String cargo = usuario.get(6);

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
                    exibirPerfil(index);
                    break;
                case 2:
                    if (cargo.equals("Supervisor") || cargo.equals("Gerente"))
                        exibirUsuarios();
                    break;

                case 3:
                    if (cargo.equals("Gerente"))
                        cadastrarUsuario();
                    break;
            }

        } while (logado);

        // Ao fazer logout, o usuário é redirecionado para o inicio
        Main.inicio();
    }

    void cadastrarUsuario() {

        System.out.print("E-mail: ");
        String user = leitor.next();

        System.out.print("Senha: ");
        String pwd = leitor.next();

        System.out.print("Nome: ");
        String nome = leitor.next();

        System.out.print("Sobrenome: ");
        String sobrenome = leitor.next();

        System.out.print("Telefone: ");
        String tel = leitor.next();

        System.out.print("CPF: ");
        String cpf = leitor.next();

        System.out.print("Cargo: ");
        String cargo = leitor.next();

        if (verificarUsuarioExiste(user)) {
            System.out.println("Usuário já existente.");
        }

        if (pwd.length() < 8) {
            System.out.println("A senha deve conter mais de 8 caracteres!");
        }

        List<String> cadastroAtual = new ArrayList<>();

        cadastroAtual.add(user);
        cadastroAtual.add(pwd);
        cadastroAtual.add(nome);
        cadastroAtual.add(sobrenome);
        cadastroAtual.add(tel);
        cadastroAtual.add(cpf);
        cadastroAtual.add(cargo);

        usuariosCadastrados.add(cadastroAtual);
        System.out.println("\nCadastro realizado com sucesso.\n");
    }

    Boolean entrar() {

        System.out.print("E-mail: ");
        String user = leitor.next();
        System.out.print("Senha: ");
        String pwd = leitor.next();

        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            List<String> usuarioAtual = usuariosCadastrados.get(i);
            if (usuarioAtual.get(0).equals(user) && usuarioAtual.get(1).equals(pwd)) {
                // ENTROU COM SUCESSO
                System.out.println("\nLogin efetuado com sucesso! Redirecionando para seu perfil...\n");
                usuarioLogado(i);
                return true;
            }
        }

        System.out.println("Login inválido, tente novamente.");
        return false;
    }


    void exibirUsuarios() {
        System.out.println("--------------------------");
        System.out.println("USUÁRIOS");

        for(int i =0; i < usuariosCadastrados.size(); i++) {

            List<String> usuario = usuariosCadastrados.get(i);

            System.out.println("""
                    --------------------------
                    Nome: %s
                    Sobrenome: %s
                    Telefone: %s
                    Email: %s
                    CPF: %s
                    Cargo: %s""".formatted(
                        usuario.get(2),
                        usuario.get(3),
                        usuario.get(4),
                        usuario.get(0),
                        usuario.get(5),
                        usuario.get(6)
                    )
            );
        }
        System.out.println("--------------------------\n");
    }

    void exibirPerfil(Integer index) {
        List<String> usuario = usuariosCadastrados.get(index);

        System.out.println("""
                \nNome: %s
                Sobrenome: %s
                Telefone: %s
                Email: %s
                CPF: %s
                Cargo: %s
                """.formatted(
                    usuario.get(2),
                    usuario.get(3),
                    usuario.get(4),
                    usuario.get(0),
                    usuario.get(5),
                    usuario.get(6)
                )
        );
    }

}
