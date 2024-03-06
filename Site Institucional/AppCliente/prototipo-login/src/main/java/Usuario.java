import java.util.ArrayList;
import java.util.List;

public class Usuario {
    List<List> usuariosCadastrados = new ArrayList<>();

    String adicionarUsuario(String user, String pwd) {
        List cadastro = new ArrayList<>();

        cadastro.add(user);
        cadastro.add(pwd);

        usuariosCadastrados.add(cadastro);

        return "Cadastro realizado com sucesso.";
    }

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
}
