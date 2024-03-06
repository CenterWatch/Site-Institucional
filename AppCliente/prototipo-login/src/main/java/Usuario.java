import java.util.ArrayList;
import java.util.List;

public class Usuario {
    List<List> usuariosCadastrados = new ArrayList<>();

    void exibirUsuarios() {
        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            System.out.println("%d. %s".formatted(i+1, usuariosCadastrados.get(i).get(0)));
        }
    }

    List<String> usuarioAtual = new ArrayList<>();
    Boolean verificarLogin(String user, String pwd) {
        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            usuarioAtual = usuariosCadastrados.get(i);
            if (usuarioAtual.get(0).equals(user) && usuarioAtual.get(1).equals(pwd)) {
                return true;
            }
        }

        return false;
    }

    Boolean verificarUsuarioExiste(String user) {
        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            usuarioAtual = usuariosCadastrados.get(i);
            if (usuarioAtual.get(0).equals(user)) {
                return true;
            }
        }

        return false;
    }

    void cadastrar(String user, String pwd) {
        Boolean cadastroValidado = validarCadastro(user, pwd);

        if (cadastroValidado) {
            List cadastro = new ArrayList<>();

            cadastro.add(user);
            cadastro.add(pwd);

            usuariosCadastrados.add(cadastro);
            System.out.println("Cadastro realizado com sucesso.");
        }
    }

    void entrar(String user, String pwd) {
        if (!(verificarLogin(user, pwd))) {
            System.out.println("Usuário ou senha inválidos.");
            return;
        }

        System.out.println("Login efetuado com sucesso.");
    }

    Boolean validarCadastro(String user, String pwd) {

        if (verificarUsuarioExiste(user)) {
            System.out.println("Usuário já existente.");
            return false;
        }

        if (pwd.length() < 8) {
            System.out.println("A senha deve conter mais de 8 caracteres!");
            return false;
        }

        return true;
    }
}
