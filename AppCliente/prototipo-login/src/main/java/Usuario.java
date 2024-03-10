import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    Scanner leitor = new Scanner(System.in);
    List<List> usuariosCadastrados = new ArrayList<>();
    Main main =  new Main();

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
        String nome = usuario.get(2);
        String cargo = usuario.get(6);

        do {
            System.out.println("Olá, %s\n".formatted(nome));

            String opcoes = "0. Sair\n1. Ver perfil";

            if (cargo.equals("Supervisor"))
                opcoes += "\n2. Ver usuários cadastrados";

            if (cargo.equals("Gerente"))
                opcoes += "\n2. Ver usuários cadastrados\n3. Cadastrar usuários";

            System.out.println(opcoes);
            System.out.println();

            switch (leitor.nextInt()) {
                case 0:
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

        Main.inicio();
    }

    void cadastrarUsuario() {

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
        List<String> cadastroAtual = new ArrayList<>();
        cadastroAtual.add("joca@gmail.com");
        cadastroAtual.add("password");
        cadastroAtual.add("Joca");
        cadastroAtual.add("Almeida");
        cadastroAtual.add("11999888777");
        cadastroAtual.add("12312312312");
        cadastroAtual.add("Gerente");
        usuariosCadastrados.add(cadastroAtual);

        System.out.printf("E-mail: ");
        String user = leitor.next();
        System.out.printf("Senha: ");
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
            System.out.println(String.format("""
                    --------------------------
                    Nome: %s
                    Sobrenome: %s
                    Telefone: %s
                    Email: %s
                    CPF: %s
                    Cargo: %s""",usuariosCadastrados.get(i).get(2),
                    usuariosCadastrados.get(i).get(3),
                    usuariosCadastrados.get(i).get(4),
                    usuariosCadastrados.get(i).get(0),
                    usuariosCadastrados.get(i).get(5),
                    usuariosCadastrados.get(i).get(6)
            ));
        }
        System.out.println("--------------------------\n");
    }

    void exibirPerfil(Integer index) {
            System.out.println(String.format("""
                    \nNome: %s
                    Sobrenome: %s
                    Telefone: %s
                    Email: %s
                    CPF: %s
                    Cargo: %s
                    """,usuariosCadastrados.get(index).get(2),
                    usuariosCadastrados.get(index).get(3),
                    usuariosCadastrados.get(index).get(4),
                    usuariosCadastrados.get(index).get(0),
                    usuariosCadastrados.get(index).get(5),
                    usuariosCadastrados.get(index).get(6)
            ));
    }

}
