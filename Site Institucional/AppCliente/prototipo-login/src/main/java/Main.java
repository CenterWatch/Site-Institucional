import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Header header = new Header();
        header.showHeader();

        Usuario usuario = new Usuario();

        usuario.adicionarUsuario("adm", "root");
        usuario.adicionarUsuario("root", "1234");
        usuario.adicionarUsuario("user", "pass");

        Scanner leitor = new Scanner(System.in);
        Scanner leitor2 = new Scanner(System.in);

        System.out.println("Bem-vindo!\n\nSelecione uma das seguintes opções:");
        System.out.println("1. Entrar");
        System.out.println("2. Cadastrar");
        System.out.println("3. Sair");

        Integer opt = leitor.nextInt();

        switch (opt) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

        System.out.println(usuario.verificarLogin(leitor2.next(), leitor2.next()));
    }
}
