import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioHandler {
    Scanner leitor = new Scanner(System.in);
    List<Usuario> usuariosCadastrados = new ArrayList<>();


    //cria os usuários pelo construtor
    public void criarUsuarios() {
        Usuario operador = new Usuario(
                "Jorge",
                "Oristano",
                "(11) 90413-4864",
                "jorge@centerwatch.com",
                "senhaJorge",
                "555888111-20",
                "operador");

        Usuario supervisor = new Usuario(
                "Reginaldo",
                "Carvalho",
                "(11) 96324-1249",
                "reginaldo@centerwatch.com",
                "senhaReginaldo",
                "666999222-30",
                "supervisor"
        );

        Usuario gerente = new Usuario(
                "Fernanda",
                "Kennedy",
                "(11) 94326-1249",
                "fernanda@centerwatch.com",
                "senhaFernanda",
                "777000333-40",
                "gerente"
        );

        //adiciona os usuários criados dentro da lista de usuarios cadastrados
        usuariosCadastrados.add(operador);
        usuariosCadastrados.add(supervisor);
        usuariosCadastrados.add(gerente);
    }

    public Boolean verificarUsuarioExiste(String email) {
        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            Usuario usuarioAtual = usuariosCadastrados.get(i);
            if (usuarioAtual.email.equalsIgnoreCase(email)) {
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

            if (cargo.equalsIgnoreCase("Supervisor"))
                opcoes += "\n2. Ver usuários cadastrados";

            if (cargo.equalsIgnoreCase("Gerente"))
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
                    if (cargo.equalsIgnoreCase("Supervisor") || cargo.equalsIgnoreCase("Gerente"))
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

        //verifica se a lista de usuarios cadastrados
        //está vazia para impedir de criar usuários repetidos
        if(usuariosCadastrados.isEmpty()) {
            criarUsuarios();
        }

        System.out.print("E-mail: ");
        String user = leitor.next();
        System.out.print("Senha: ");
        String pwd = leitor.next();

        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            Usuario usuarioAtual = usuariosCadastrados.get(i);
            if (usuarioAtual.email.equals(user) && usuarioAtual.senha.equalsIgnoreCase(pwd)) {
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
